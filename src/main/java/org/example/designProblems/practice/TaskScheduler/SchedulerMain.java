package org.example.designProblems.practice.TaskScheduler;

import java.time.LocalDateTime;

public class SchedulerMain {
    public static void main(String[] args) {
        AbstractTask emailTask = new EmailTask(1,"Email send", LocalDateTime.now(),0);
        AbstractTask dbclean = new DBCleanupTask(2,"db clean",LocalDateTime.now().plusSeconds(10),1000);
        TaskScheduler taskScheduler = TaskScheduler.getInstance();
        taskScheduler.addTask(emailTask);
        taskScheduler.addTask(dbclean);

        taskScheduler.run();
    }
}
