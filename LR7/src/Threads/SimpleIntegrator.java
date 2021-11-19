package Threads;

import functions.Functions;

public class SimpleIntegrator implements Runnable{
    private Task t;

    @Override
    public void run() {
        for (int i = 0; i < t.getTask(); i++) {
            double res;
            if(t.f1 == null){
                continue;
            }
            synchronized (t){
                res = Functions.integrate(t.f1, t.leftX, t.rightX, t.step);
                System.out.println("Result leftX = " + t.leftX + " rightX = " + t.rightX + " step = " + t.step + " result of integrate = " + res);
            }
        }

    }
    public SimpleIntegrator(Task t1){
        this.t = t1;
    }

}
