package Threads;

import functions.Function;

public class Task {
    public Function f1;
    public double leftX, rightX, step;
    private int tasks;

    public Task(int a){
        if(a <= 0){
            throw new IllegalArgumentException();
        }
        this.tasks = a;
    }
    public int getTask(){
        return this.tasks;
    }
}
