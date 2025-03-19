package org.example.designProblems.practice.TaskScheduler;

import java.time.LocalDateTime;

public class DBCleanupTask extends AbstractTask{
    public DBCleanupTask(Integer id, String name, LocalDateTime time, long interval) {
        super(id, name, time, interval);
    }

    @Override
    public void execute() {
        System.out.println("Testing db cleanup");
    }
}
