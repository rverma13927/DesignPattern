package org.example.DesignPattern.Singleton;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A thread pool manages a set of worker threads to execute tasks concurrently. A Singleton can
 * ensure that the thread pool is shared across the application.
 *
 * Interview Question:
 * "Implement a Thread Pool using the Singleton pattern. The pool should allow submitting tasks for execution."
 */
public class ThreadPoolManager {
    private static ThreadPoolManager instance;
    private ExecutorService executorService;
    private static final int THREAD_POOL_SIZE = 10;

    private ThreadPoolManager() {
        executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    }

    public static ThreadPoolManager getInstance() {
        if (instance == null) {
            synchronized (ThreadPoolManager.class) {
                if (instance == null) {
                    instance = new ThreadPoolManager();
                }
            }
        }
        return instance;
    }

    public void submitTask(Runnable task) {
        executorService.submit(task);
    }

    public void shutdown() {
        executorService.shutdown();
    }

    public static void main(String[] args) {
        ThreadPoolManager threadPool = ThreadPoolManager.getInstance();

        for (int i = 0; i < 15; i++) {
            threadPool.submitTask(() -> {
                System.out.println("Task executed by " + Thread.currentThread().getName());
            });
        }

        threadPool.shutdown();
    }
}