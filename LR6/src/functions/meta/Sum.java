package functions.meta;

import functions.Function;

public class Sum implements Function {
    Function first, second;
    public Sum(Function f1, Function f2)
    {
        this.first = f1;
        this.second = f2;
    }
    public double getLeftDomainBorder() {
        return Math.max(first.getLeftDomainBorder(), second.getLeftDomainBorder());
    }

    public double getRightDomainBorder() {
        return Math.min(first.getRightDomainBorder(), second.getRightDomainBorder());
    }

    public double getFunctionValue(double x) {
        return (first.getFunctionValue(x) + second.getFunctionValue(x));
    }
}
