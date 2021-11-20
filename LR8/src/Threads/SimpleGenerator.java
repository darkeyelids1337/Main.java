package Threads;

import functions.basic.Log;

public class SimpleGenerator implements Runnable{
    private Task t;
    @Override
    public void run() {
        for (int i = 0; i < t.getTask(); i++) {
            Log l = new Log(1 +(Math.random() * 9));
            synchronized (t){
                t.f1 = l;
                t.leftX = Math.random() * 100;
                t.rightX = Math.random() * 100 + 100;
                t.step = Math.random();
                System.out.println("Source leftX = " + t.leftX + " rightX = " + t.rightX + " step = " + t.step );
            }

        }
    }
    public SimpleGenerator(Task t1){
        this.t = t1;
    }
}
