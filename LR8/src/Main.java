import Threads.*;
import functions.*;
import functions.basic.*;
import java.io.*;

public class Main {
    public static void nonThread(){
        Task t = new Task(100);
        for (int i = 0; i < t.getTask(); i++){
            t.f1 = new Log(1 + (Math.random() * 9));
            t.leftX = Math.random() * 100;
            t.rightX = Math.random() * 100 + 100;
            t.step = Math.random();
            System.out.println("Source leftX = " + t.leftX + " rightX = " + t.rightX + " step = " + t.step);
            double res = Functions.integrate(t.f1, t.leftX, t.rightX, t.step);
            System.out.println("Result leftX = " + t.leftX + " rightX = " + t.rightX + " step = " + t.step + " result of integrate = " + res);
        }
    }
    public static void simpleThreads(){
        Task t = new Task(100);
        Thread generator = new Thread(new SimpleGenerator(t));
        generator.start();
        Thread integrator = new Thread(new SimpleIntegrator(t));
        integrator.start();
    }
    public static void complicatedThreads() throws InterruptedException {
        Task t = new Task(100);
        Semaphore s = new Semaphore();
        Thread generator = new Thread(new Generator(t,s));
        Thread integrator = new Thread(new Integrator(t, s));
        generator.start();
        integrator.start();
        Thread.sleep(50);
        generator.interrupt();
        integrator.interrupt();

    }

    public static void main(String[] args) throws InappropriateFunctionPointException, CloneNotSupportedException, InterruptedException {
       // Function f = new Cos();
        //TabulatedFunction tf;
        //tf = TabulatedFunctions.tabulate(f, 0, Math.PI, 11);
        //System.out.println(tf.getClass());
        //TabulatedFunctions.setTabulatedFunctionFactory(new
         //       LinkedListTabulatedFunction.LinkedListTabulatedFunctionFactory());
        //tf = TabulatedFunctions.tabulate(f, 0, Math.PI, 11);
        //System.out.println(tf.getClass());
        //TabulatedFunctions.setTabulatedFunctionFactory(new
          //      ArrayTabulatedFunction.ArrayTabulatedFunctionFactory());
        //tf = TabulatedFunctions.tabulate(f, 0, Math.PI, 11);
        //System.out.println(tf.getClass());
        TabulatedFunction f;
        f = TabulatedFunctions.createTabulatedFunction(
                ArrayTabulatedFunction.class, 0, 10, 3);
        System.out.println(f.getClass());
        System.out.println(f);
        f = TabulatedFunctions.createTabulatedFunction(
                ArrayTabulatedFunction.class, 0, 10, new double[] {0, 10});
        System.out.println(f.getClass());
        System.out.println(f);
        f = TabulatedFunctions.createTabulatedFunction(
                LinkedListTabulatedFunction.class,
                new FunctionPoint[] {
                        new FunctionPoint(0, 0),
                        new FunctionPoint(10, 10)
                }
        );
        System.out.println(f.getClass());
        System.out.println(f);
        f = TabulatedFunctions.tabulate(
                LinkedListTabulatedFunction.class, new Sin(), 0, Math.PI, 11);
        System.out.println(f.getClass());
        System.out.println(f);
    }
}