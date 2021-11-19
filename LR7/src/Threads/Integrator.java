package Threads;

import functions.Functions;

public class Integrator extends Thread {
    private Task t;
    private Semaphore s;
    private boolean isrun = false;


    public Integrator(Task t1, Semaphore s2){
        this.t = t1;
        this.s = s2;
    }

    @Override
    public void run() {
        isrun = true;
        for (int i = 0; i < t.getTask() && isrun; i++) {
            try {
                s.beginRead();
                double res = Functions.integrate(t.f1, t.leftX, t.rightX, t.step);
                System.out.println("Result leftX = " + t.leftX + " rightX = " + t.rightX + " step = " + t.step + " integrate = " + res);
                s.endRead();
            } catch (InterruptedException e) {
                System.out.println("Integrator is interrupted");
            }
        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        isrun = false;
    }
}
