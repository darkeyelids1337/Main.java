package functions.meta;

import functions.Function;

public class Power implements Function {
    private Function first;
    private double second;
    public Power(Function f1, double f2)
    {
        this.first = f1;
        this.second = f2;
    }
    public double getLeftDomainBorder() {
        return first.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return first.getRightDomainBorder();
    }

    public double getFunctionValue(double x) {
        return Math.pow(first.getFunctionValue(x), second);
    }
}
