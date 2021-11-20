package functions.meta;

import functions.Function;

public class Scale implements Function {
    private Function first;
    private double mx, my;
    public Scale(Function f1, double x, double y)
    {
        this.first = f1;
        this.mx = x;
        this.my = y;
    }
    public double getLeftDomainBorder() {
        if (mx >= 0)
        {
            return mx * first.getLeftDomainBorder();
        }
        else
        {
            return (first.getLeftDomainBorder() / mx);
        }
    }

    public double getRightDomainBorder() {
        if (mx >= 0)
        {
            return mx * first.getRightDomainBorder();
        }
        else
        {
            return (first.getRightDomainBorder() / mx);
        }
    }
    public double getFunctionValue(double x) {
       if (my >= 0 )
       {
           return my * first.getFunctionValue(x/mx);
       }
       else
       {
           return first.getFunctionValue(x/mx) / my;
       }
    }

}
