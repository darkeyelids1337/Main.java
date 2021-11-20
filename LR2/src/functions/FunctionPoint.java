package functions;
public class FunctionPoint {
    public double x;
    public double y;
   public  FunctionPoint(double x, double y)
    {
     this.x = x;
     this.y = y;
    }
    public FunctionPoint(FunctionPoint point)
    {
        FunctionPoint P = new FunctionPoint(this.x, this.y);
    }
   public FunctionPoint()
    {
        this.x = 0;
        this.y = 0;
    }

}