package functions;

import java.util.Arrays;

public class ArrayTabulatedFunction implements TabulatedFunction {
    private FunctionPoint[] tochka;
    private int counttochk;
    public ArrayTabulatedFunction(FunctionPoint[] array)
    {

        if (array.length < 2) {
            throw new IllegalArgumentException();
        }
        counttochk = array.length;
        for (int i = 1; i < array.length; i++) {
            if (array[i - 1].x > array[i].x) {
                throw new IllegalArgumentException();
            }
        }
        tochka = new FunctionPoint[array.length + array.length / 2];
        System.arraycopy(array, 0, tochka, 0, array.length);
    }
    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount)
    {
        if(leftX >= rightX || pointsCount < 2){
            try {
                throw new IllegalArgumentException();
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Ошибка данных");
            }
        }
        double prom = (rightX - leftX)/(pointsCount - 1);
        counttochk = pointsCount;
        tochka = new FunctionPoint[pointsCount];
        double x = leftX;
        for(int i = 0; i < pointsCount; i++){
            tochka[i] = new FunctionPoint(x, 0);
            x += prom;
        }
    }
    public ArrayTabulatedFunction(double leftX, double rightX, double[] values)
    {
        if(leftX >= rightX || values.length < 2){
            try {
                throw new IllegalArgumentException();
            }
            catch (IllegalArgumentException e)
            {
                System.out.println("Ошибка данных");
            }
        }
        counttochk = values.length;
        tochka = new FunctionPoint[values.length];
        double prom = (rightX - leftX)/(values.length - 1);
        double x = leftX;
        for(int i = 0; i < values.length; i++)
        {
            tochka[i] = new FunctionPoint(x, values[i]);
            x+=prom;
        }
    }
    public double getLeftDomainBorder()
    {
        return tochka[0].x;
    }
    public double getRightDomainBorder()
    {
        return tochka[counttochk - 1].x;
    }
    public double getFunctionValue(double X)
    {
        if (X >= this.getLeftDomainBorder() && X <= this.getRightDomainBorder()){
            for(int i = 0; i < counttochk; i++){
                if (X == tochka[i].x){
                    return tochka[i].y;
                }
            }
            int i = 0;
            while(X > tochka[i].x){
                i++;
            }
            double k = (tochka[i].y - tochka[i-1].y)/(tochka[i].x - tochka[i-1].x);
            double m = tochka[i].y - k * tochka[i].x;
            return k * X+ m;
        }
        return Double.NaN;
    }
    public int getPointsCount()
    {
        return counttochk;
    }
    public FunctionPoint getPoint(int index)
    {
        if (index > counttochk || index < 0)
        {
            try {
                throw new FunctionPointIndexOutOfBoundsException();
            }
            catch (FunctionPointIndexOutOfBoundsException e) {
                System.out.println("Выход за границы");
            }
        }
        return tochka[index];
    }
    public void setPoint(int index, FunctionPoint point) throws InappropriateFunctionPointException {
        if (index > counttochk || index < 0) {
            try {
                throw new FunctionPointIndexOutOfBoundsException();
            } catch (FunctionPointIndexOutOfBoundsException e) {
                System.out.println("Выход за границы");
            }
        }
        double inter = (tochka[counttochk-1].x-tochka[0].x)/(counttochk-1);
        if( (point.x - tochka[0].x) % inter == 0) {
            tochka[index].x = point.x;
            tochka[index].y = point.y;
        }
        else {
            throw new InappropriateFunctionPointException();
        }

    }
    public double getPointX(int index)
    {
        if (index > counttochk || index < 0) {
            try {
                throw new FunctionPointIndexOutOfBoundsException();
            } catch (FunctionPointIndexOutOfBoundsException e) {
                System.out.println("Выход за границы");
            }
        }
        return tochka[index].x;
    }
    public void setPointX(int index, double X) throws InappropriateFunctionPointException {
        if (index > counttochk || index < 0) {
            try {
                throw new FunctionPointIndexOutOfBoundsException();
            } catch (FunctionPointIndexOutOfBoundsException e) {
                System.out.println("Выход за границы");
            }
        }
        double inter = (tochka[counttochk-1].x-tochka[0].x)/(counttochk-1);
        if (X >= this.tochka[index - 1].x && X <= this.tochka[index + 1].x)
        {
            tochka[index].x = X;
        }
        else throw new InappropriateFunctionPointException();
    }
    public double getPointY(int index)
    {
        if (index > counttochk || index < 0) {
            try {
                throw new FunctionPointIndexOutOfBoundsException();
            } catch (FunctionPointIndexOutOfBoundsException e) {
                System.out.println("Выход за границы");
            }
        }
        return tochka[index].y;
    }
    public void setPointY(int index, double Y)
    {
        if (index > counttochk || index < 0) {
            try {
                throw new FunctionPointIndexOutOfBoundsException();
            } catch (FunctionPointIndexOutOfBoundsException e) {
                System.out.println("Выход за границы");
            }
        }
        tochka[index].y = Y;
    }
    public void deletePoint(int index) throws FunctionPointIndexOutOfBoundsException, IllegalStateException
    {
        if (index > counttochk || index < 0) {
            throw new FunctionPointIndexOutOfBoundsException();
        }
        if (counttochk < 3)
        {
            throw new IllegalStateException();
        }
        else {
            System.arraycopy(tochka, index + 1, tochka, index, getPointsCount() - index - 1);
            --counttochk;

        }
    }
    public void addPoint(FunctionPoint point) throws InappropriateFunctionPointException {
        int first = 0;
        int last = getPointsCount();
        int mid = first + (last - first) / 2;
        while(first < last){
            if(point.x == tochka[mid].x) throw new InappropriateFunctionPointException();
            if(point.x < tochka[mid].x){
                last = mid;
            } else {
                first = mid + 1;
            }
            mid = first + (last - first) / 2;
        }
        if(counttochk >= tochka.length){
            FunctionPoint [] res = new FunctionPoint[counttochk + counttochk / 2];
            System.arraycopy(tochka, 0, res, 0, counttochk);
            tochka = res;
        }
        System.arraycopy(tochka, last, tochka, last + 1, getPointsCount() - last);
        tochka[last] = point;
        ++counttochk;
    }
    public String toString()
    {
        StringBuilder skobka = new StringBuilder("{");
        for (int i = 0; i < this.counttochk; i++)
        {
            skobka.append(this.tochka[i].toString()).append(", ");
        }
        skobka.deleteCharAt(skobka.length() - 1).deleteCharAt(skobka.length() - 1);
        skobka.append("}");
        return skobka.toString();
    }
    public boolean equals(Object o)
    {
        if (o instanceof TabulatedFunction)
        {
            if (o instanceof ArrayTabulatedFunction)
            {
                ArrayTabulatedFunction f1 = (ArrayTabulatedFunction) o;
                if(this.counttochk != f1.counttochk)
                {
                    return false;
                }
                for (int i = 0; i < counttochk; i++)
                {
                    if(f1.tochka[i].x != this.tochka[i].x)
                    {
                        return false;
                    }
                    if(f1.tochka[i].y != this.tochka[i].y){
                        return false;
                    }
                }
                return true;
            }
            else{
                TabulatedFunction f1 = (TabulatedFunction) o;
                if (f1.getPointsCount() != this.counttochk){
                    return false;
                }
                for (int i = 0; i < this.counttochk; i++)
                {
                    if(!(this.getPoint(i).equals(f1.getPoint(i))))
                    {
                        return false;
                    }
                }
                return true;
            }
        } else{
            return false;
        }
    }
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Arrays.deepHashCode(this.tochka);
        hash = 31 * hash + this.counttochk;
        return hash;
    }
    public Object clone() throws CloneNotSupportedException
    {
        FunctionPoint [] tochka = new FunctionPoint[counttochk];
        for (int i =0; i < this.counttochk; i++)
        {
            tochka[i] = (FunctionPoint) this.tochka[i].clone();
        }
        return new ArrayTabulatedFunction(tochka);
    }
}
