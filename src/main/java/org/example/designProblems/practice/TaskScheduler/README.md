Task Scheduler

# Requirements:

1. User should be able to schedule the task(periodically,at once, at particular date/time)
2. System should support multiple task at a time or multithreading.
3. Use queue so that system should not get overloaded.



# Entites:
1. User
2. Schedule
3. Schedule type : recurring,at once,at particular time


# Functional:
1. Concurrency: handle multiple task running simultaneously without confilcts
2. Retries if fails.




class:

user,
scheudle: time,isrecurring,pattern..
Schedule type : recurring,at once,at particular time

scheduler class-- schedule()

repository: 

executor:


Task:(execute())
    AbstractTask(id,name,Localdatetime,interval)
        : EmailTask
        : GenerateReportTask
        : DBCleanUpTask

TaskScheduler: Singleton
        : run()
        : Task task
        : priority_queue min-heap
        : addTask
        
