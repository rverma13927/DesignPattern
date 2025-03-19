package org.example.designProblems.practice.TaskScheduler;

import java.time.LocalDateTime;

public class EmailTask extends AbstractTask{
    public EmailTask(Integer id, String name, LocalDateTime time, long interval) {
        super(id, name, time, interval);
    }

    @Override
    public void execute() {
        System.out.println("Hello world");
    }
}
