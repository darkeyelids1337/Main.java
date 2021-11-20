package functions;
import functions.meta.*;

public class Functions {
    private Functions()
    {}
    public static Function shift(Function f, double shiftX, double shiftY)
    {
        return new Shift(f,shiftX,shiftY);
    }
    public static Function scale(Function f, double scaleX, double scaleY) {
        return new Scale(f, scaleX, scaleY);
    }

    public static Function power(Function f, double power) {
        return new Power(f, power);
    }

    public static Function sum(Function f1, Function f2) {
        return new Sum(f1, f2);
    }

    public static Function mult(Function f1, Function f2) {
        return new Mult(f1,f2);
    }

    public static Function composition(Function f1, Function f2) {
        return new Composition(f1, f2);
    }

    public static double Integrate(Function f1, double leftX, double rightX, double step){
        if(leftX< f1.getLeftDomainBorder() || rightX > f1.getRightDomainBorder()){
            throw new IllegalArgumentException();
        }
        double res = 0, current = leftX;
        while(current < rightX){
            res += (f1.getFunctionValue(current) + f1.getFunctionValue(current + step)) * step / 2;
            current += step;
        }
        current -= step;
        res += (f1.getFunctionValue(rightX) + f1.getFunctionValue(current)) * (rightX - current) / 2;
        return res;
    }

}
