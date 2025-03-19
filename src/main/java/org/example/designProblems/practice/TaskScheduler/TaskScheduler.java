package org.example.designProblems.practice.TaskScheduler;

import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;

public class TaskScheduler {
    private TaskScheduler(){}
    private static PriorityQueue<AbstractTask> priorityQueue = new PriorityQueue<>((task1,task2)->{
         return task1.getRunTime().compareTo(task2.getRunTime());
    });
    private static TaskScheduler taskScheduler;

    public static TaskScheduler getInstance(){
         if(taskScheduler==null){
             synchronized (TaskScheduler.class){
                 if(taskScheduler==null){
                     taskScheduler= new TaskScheduler();
                 }
             }
         }
         return taskScheduler;
    }
    public void addTask(AbstractTask task){
        priorityQueue.add(task);
    }
    public void run(){
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
               while(!priorityQueue.isEmpty()){
                   AbstractTask task = priorityQueue.peek();
                   if(task.getRunTime().isBefore(LocalDateTime.now())|| task.getRunTime().equals(LocalDateTime.now())){
                       task.execute();
                       priorityQueue.poll();
                       if(task.getInterval()>0){
                           task.setRunTime(LocalDateTime.now().plusSeconds(task.getInterval()/1000));
                           priorityQueue.add(task);
                       }
                   }
               }
            }
        };

        timer.scheduleAtFixedRate(timerTask,0,10000);
    }
}
