package Threads;

import functions.basic.Log;

public class Generator extends Thread{
    private Task t;
    private Semaphore s;
    private boolean isrun = false;

    public Generator(Task t1, Semaphore s2){
        this.t = t1;
        this.s = s2;
    }

    @Override
    public void run() {
        isrun = true;
        for(int i = 0; i < t.getTask() && isrun; i++){
            try{
                Log l = new Log(1 + (Math.random() * 9));
                s.beginWrite();
                t.f1 = l;
                t.leftX = Math.random() * 100;
                t.rightX = Math.random() * 100 + 100;
                t.step = Math.random();
                s.endWrite();
                System.out.println("Source leftX = " + t.leftX + " rightX = " + t.rightX + " step = " + t.step);
            } catch (InterruptedException e){
                System.out.println("Generator is interrupted");
            }

        }
    }

    @Override
    public void interrupt() {
        super.interrupt();
        isrun = false;
    }
}
