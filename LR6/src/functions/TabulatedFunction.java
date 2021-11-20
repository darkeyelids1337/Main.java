package functions;
import java.io.IOException;
import java.io.Serializable;

public interface TabulatedFunction extends Function, Serializable, Cloneable {
    public int getPointsCount();

    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException, IOException;

    public double getPointX(int index);

    public void setPointX(int index, double x) throws InappropriateFunctionPointException, IOException;

    public double getPointY(int index);

    public void setPointY(int index, double y) throws IOException;

    public void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException, IllegalStateException;

    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException;


    public FunctionPoint getPoint(int index) throws FunctionPointIndexOutOfBoundsException;

    public Object clone() throws CloneNotSupportedException;
}
