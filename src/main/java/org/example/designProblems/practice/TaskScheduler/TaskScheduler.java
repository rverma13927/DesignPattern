package org.example.designProblems.practice.TaskScheduler;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;

public class TaskScheduler {

    private static PriorityBlockingQueue<AbstractTask> priorityQueue = new PriorityBlockingQueue<>(10, (o1, o2) -> o1.getRunTime().compareTo(o2.getRunTime()));
    private static ExecutorService executorService;
    private static TaskScheduler taskScheduler;
    private TaskScheduler(){
        executorService = Executors.newFixedThreadPool(10);
    }
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
    public   void  run(){
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                synchronized (priorityQueue) {
                    while (!priorityQueue.isEmpty()) {
                        AbstractTask task = priorityQueue.peek();


                            if (task.getRunTime().isBefore(LocalDateTime.now()) || task.getRunTime().equals(LocalDateTime.now())) {
                                executorService.execute(() -> {
                                try {
                                    task.execute();
                                } catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                });
                                priorityQueue.poll();
                                if (task.getInterval() > 0) {
                                    task.setRunTime(LocalDateTime.now().plusSeconds(task.getInterval() / 1000));
                                    priorityQueue.add(task);
                                }
                            }


                    }
                }
            }
        };


        timer.scheduleAtFixedRate(timerTask,0,10000);
    }
}
