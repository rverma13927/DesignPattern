package org.example.designProblems.practice.TaskScheduler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public abstract class AbstractTask implements Task{
    private Integer id;
    private String name;
    private LocalDateTime runTime;
    private long interval; // interval in milliseconds for recurring tasks
}
