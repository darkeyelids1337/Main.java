package functions;

public class TabulatedFunction {
    private FunctionPoint[] tochka;
    private int counttochk;
    public TabulatedFunction(double leftX, double rightX, int pointsCount)
    {
        double prom = (rightX - leftX)/(pointsCount - 1);
        counttochk = pointsCount;
        tochka = new FunctionPoint[pointsCount];
        double x = leftX;
        for(int i = 0; i < pointsCount; i++){
            tochka[i] = new FunctionPoint(x, 0);
            x += prom;
        }

    }
   public TabulatedFunction(double leftX, double rightX, double[] values)
    {
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
       return tochka[index];
    }
    public void setPoint(int index, FunctionPoint point)
    {
        double inter = (tochka[counttochk-1].x-tochka[0].x)/(counttochk-1);
        if( (point.x - tochka[0].x) % inter == 0) {
            tochka[index].x = point.x;
            tochka[index].y = point.y;
        }
        else {
            System.out.println("NaN");
        }

    }
    public double getPointX(int index)
    {
        return tochka[index].x;
    }
    public void setPointX(int index, double X)
    {
        double inter = (tochka[counttochk-1].x-tochka[0].x)/(counttochk-1);
        if ( (X - tochka[0].x) % inter == 0)
        {
            tochka[index].x = X;
        }
        else System.out.println("NaN");
    }
    public double getPointY(int index)
    {
         return tochka[index].y;
    }
    public void setPointY(int index, double Y)
    {
        tochka[index].y = Y;
    }
    public void deletePoint(int index)
    {
        FunctionPoint [] res = new FunctionPoint[counttochk - 1];
        System.arraycopy(tochka, 0, res, 0 , index);
        System.arraycopy(tochka, index +1, res ,index, counttochk - index -1 );
        for(int i = 0; i < counttochk-1 ; i++) {
            System.out.print(res[i].x);
            System.out.print("   ");
            System.out.println(res[i].y);
        }
    }
        public void addPoint(FunctionPoint point)
        {
        double inter = (tochka[counttochk-1].x - tochka[0].x)/(counttochk-1);
        FunctionPoint [] res = new FunctionPoint[counttochk+1];
        System.arraycopy(tochka, 0, res, 0 , counttochk);
        if ((point.x - tochka[0].x) % inter == 0)
        {
            res[counttochk] = point;

        }
        else
        {
            System.out.println("NaN");
        }
    }
}
