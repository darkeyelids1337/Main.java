import functions.FunctionPoint;
import functions.TabulatedFunction;

public class Main {
    public static void main(String [] args)
    {
        TabulatedFunction P;
        double[] arr = {1, 0, 1};
        P = new TabulatedFunction(-3, 3, arr);
        FunctionPoint p = new FunctionPoint(0, 5);
        System.out.println("Левая граница " + P.getLeftDomainBorder() + " ");
        System.out.println("Правая граница " + P.getRightDomainBorder() + " ");
        for (int i = 0; i < P.getPointsCount(); i++) {

            System.out.print("Точка " + (i + 1) + " x = " + P.getPointX(i) + " ");

        }
        System.out.println();
        System.out.println(" Y = " + P.getFunctionValue(2.5));
        System.out.println("Кол-во точек " + P.getPointsCount());
        System.out.println("Добавляем новую точку c координатами (0 , 5)");
        P.addPoint(p);
        System.out.println("Кол-во точек " + P.getPointsCount());
        for (int i = 0; i < P.getPointsCount(); i++) {

            System.out.print("Точка " + (i + 1) + " x = " + P.getPointX(i) + " ");


        }
        System.out.println();
        FunctionPoint p1 = new FunctionPoint(1.5, 5);
        System.out.println("Добавляем новую точку c координатами (1.5 , 5)");
        P.addPoint(p1);
        System.out.println("Кол-во точек " + P.getPointsCount());
        for (int i = 0; i < P.getPointsCount(); i++) {

            System.out.print("Точка " + (i + 1) + " x = " + P.getPointX(i) + " ");


        }
        System.out.println();
        System.out.println("Удаляем точку с индексом 5");
        P.deletePoint(2);
        System.out.println("Кол-во точек " + P.getPointsCount());
        for (int i = 0; i < P.getPointsCount(); i++) {

            System.out.print("Точка " + (i + 1) + " x = " + P.getPointX(i) + " ");

        }

    }
}
