package Class_Activity_4.Part3_Java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorDemo {
    public static void main(String[] args) {
        // 1. SingleThreadExecutor: Tasks run one by one
        System.out.println("--- SingleThreadExecutor ---");
        ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
        for (int i = 1; i <= 3; i++) {
            final int taskID = i;
            singleExecutor.execute(() -> 
                System.out.println("Task " + taskID + " executed by " + Thread.currentThread().getName())
            );
        }
        singleExecutor.shutdown();

        // 2. CachedThreadPool: Creates new threads as needed (parallel)
        System.out.println("\n--- CachedThreadPool ---");
        ExecutorService cachedExecutor = Executors.newCachedThreadPool();
        for (int i = 1; i <= 3; i++) {
            final int taskID = i;
            cachedExecutor.execute(() -> 
                System.out.println("Task " + taskID + " executed by " + Thread.currentThread().getName())
            );
        }
        cachedExecutor.shutdown();
    }
}