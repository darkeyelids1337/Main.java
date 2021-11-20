package TFDpackgae;
import functions.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class TabulatedFunctionDoc implements TabulatedFunction {
    private TabulatedFunction f1;
    private String fileName = "";
    private boolean modified = false;
    private boolean fileNameAssigned = false;
    private FXMLMainFormController ctrl;

    public void newFunction(double leftX, double rightX, int pointsCount){
        f1 = new ArrayTabulatedFunction(leftX, rightX, pointsCount);
        modified = false;
    }
    public void tabulateFunction(Function function, double leftX, double rightX, int pointsCount){
        f1 = TabulatedFunctions.tabulate(function,leftX,rightX,pointsCount);
        modified = false;
    }
    public void saveFunction() throws IOException{
        FileWriter writer = new FileWriter(fileName);
        JSONObject pointJSON = new JSONObject();
        JSONArray pointArray = new JSONArray();
        JSONArray funArray = new JSONArray();
        for (int i = 0; i < getPointsCount(); i++) {
            pointArray.add(getPointX(i));
            pointArray.add(getPointY(i));
            pointJSON.put("p" + i, pointArray);
            pointArray = new JSONArray();
        }
        funArray.add(pointJSON);
        writer.write(funArray.toJSONString());
        writer.flush();
        modified = false;
    }

    public void saveFunctionAs(String fileName) throws IOException{
        this.fileName = fileName;
        fileNameAssigned = true;
        saveFunction();
        modified = false;
    }
    public void loadFunction(String fileName) throws FileNotFoundException, IOException, ParseException, InappropriateFunctionPointException {
        this.fileName = fileName;
        fileNameAssigned = true;
        FileReader reader = new FileReader(fileName);
        JSONParser jsonParser = new JSONParser();
        Object obj = jsonParser.parse(reader);
        JSONArray jsonArray = (JSONArray) obj;
        JSONObject point = (JSONObject) jsonArray.get(0);
        FunctionPoint [] points = new FunctionPoint[point.size()];
        FunctionPoint pi = new FunctionPoint();
        for (int i = 0; i < point.size(); i++){
            JSONArray fin = (JSONArray) point.get("p" + i);
            pi.x = Double.parseDouble(fin.get(0).toString());
            pi.y = Double.parseDouble(fin.get(1).toString());
            points[i] = new FunctionPoint(pi);
        }
        modified = false;

    }
    public boolean isModified() {
        return modified;
    }

    public boolean isFileNameAssigned() {
        return fileNameAssigned;
    }
    public double getLeftDomainBorder() {
        return f1.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return f1.getRightDomainBorder();
    }


    public double getFunctionValue(double x) {
        return f1.getFunctionValue(x);
    }


    public int getPointsCount() {
        return f1.getPointsCount();
    }


    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException, IOException {
        f1.setPoint(index,point);
        modified = false;
        callRedraw();
    }


    public double getPointX(int index) {
        return f1.getPointX(index);
    }


    public void setPointX(int index, double x) throws InappropriateFunctionPointException, IOException {
        f1.setPointX(index,x);
        modified = true;
        callRedraw();
    }


    public double getPointY(int index) {
        return f1.getPointY(index);
    }


    public void setPointY(int index, double y) throws IOException {
        f1.setPointY(index,y);
        modified = true;
        callRedraw();
    }


    public void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException{
        f1.deletePoint(index);
        modified = true;
        callRedraw();
    }

    public void addPoint(FunctionPoint point) {
       try{
           f1.addPoint(point);
           callRedraw();
           modified = true;
       } catch (InappropriateFunctionPointException e){
           e.printStackTrace();
       }
    }


    public FunctionPoint getPoint(int index) {
        return new FunctionPoint(f1.getPoint(index));
    }
    public String toString() {
        StringBuilder skobka = new StringBuilder("{");
        for (int i = 0; i < f1.getPointsCount(); i++)
        {
            skobka.append(f1.getPoint(i).toString()).append(", ");
        }
        skobka.deleteCharAt(skobka.length() - 1).deleteCharAt(skobka.length() - 1);
        skobka.append("}");
        return skobka.toString();
    }
    public Object clone() throws CloneNotSupportedException {
        TabulatedFunctionDoc clone = new TabulatedFunctionDoc();
        clone.f1 = (TabulatedFunction) f1.clone();
        clone.fileName = fileName;
        clone.modified = modified;
        clone.fileNameAssigned = fileNameAssigned;
        return clone();
    }

    public boolean equals(Object o){
        if (o instanceof TabulatedFunction)
        {
            TabulatedFunction function = (TabulatedFunction) o;
            if (function.getPointsCount() != this.getPointsCount()){
                return false;
            }
            for(int i =0; i < this.getPointsCount(); i++){
                if(!(this.getPoint(i).equals(function.getPoint(i)))){
                    return false;
                }
            }
            return true;
        }
        else return false;
    }
    public void registerRedrawFunctionController(FXMLMainFormController fxmlMainFormConroller) {
        ctrl = fxmlMainFormConroller;
        callRedraw();
    }
    public void callRedraw()  {
        ctrl.redraw();
    }

}
