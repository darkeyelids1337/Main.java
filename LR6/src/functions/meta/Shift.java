package functions.meta;

import functions.Function;

public class Shift implements Function {
    private Function first;
    private double mx, my;
    public Shift(Function f1, double x, double y)
    {
        this.first = f1;
        this.mx = x;
        this.my = y;
    }
    public double getLeftDomainBorder(){
        return mx + first.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return mx + first.getRightDomainBorder();
    }
    public double getFunctionValue(double x) {
        return my + first.getFunctionValue(x - mx);
    }
}
