import functions.*;

public class Main {
    public static void main(String[] args) throws InappropriateFunctionPointException {
        LinkedListTabulatedFunction Parabola;
        double[] arr = {1, 0, 1};
/*        Parabola = new ArrayTabulatedFunction(-3, 3, arr);
        System.out.println("Левая граница " + Parabola.getLeftDomainBorder());
        System.out.println("Правая граница " + Parabola.getRightDomainBorder());
        for (int i = 0; i < Parabola.getPointsCount(); i++) {
            System.out.print("Точка " + (i + 1) + " x = " + Parabola.getPointX(i));
            System.out.println("\n       " + " y = " + Parabola.getPointY(i));
        }
*/
        Parabola = new LinkedListTabulatedFunction(-3, 3, arr);
        System.out.println("Левая граница " + Parabola.getLeftDomainBorder());
        System.out.println("Правая граница " + Parabola.getRightDomainBorder());
        Parabola.addPoint(new FunctionPoint(1.5, 5));
        for (int i = 0; i < Parabola.getPointsCount(); i++) {
            System.out.print("Точка " + (i + 1) + " x = " + Parabola.getPointX(i));
            System.out.println("\n       " + " y = " + Parabola.getPointY(i));
        }
        Parabola.deletePoint(2);
        for (int i = 0; i < Parabola.getPointsCount(); i++) {
            System.out.print("Точка " + (i + 1) + " x = " + Parabola.getPointX(i));
            System.out.println("\n       " + " y = " + Parabola.getPointY(i));
        }

    }
}
