package org.example.designProblems.practice.TaskScheduler;

import java.time.LocalDateTime;

public class DBCleanupTask extends AbstractTask{
    public DBCleanupTask(Integer id, String name, LocalDateTime time, long interval) {
        super(id, name, time, interval);
    }

    @Override
    public void execute() throws InterruptedException {
        System.out.println("Testing " + this.getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
