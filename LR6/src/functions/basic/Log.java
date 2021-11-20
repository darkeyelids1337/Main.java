package functions.basic;

import functions.Function;

public class Log implements Function {
    private double base;
    public Log(double bs)
    {
        if (bs > 0 && bs != 1)
        {
            this.base = bs;
        }
    }
    public double getLeftDomainBorder() {
        return 0;
    }

    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }

    public double getFunctionValue(double x) {
        if( x > 0)
        {
            return (Math.log(x)/Math.log(base));
        }
        else
        {
            return Double.NaN;
        }
    }

}
