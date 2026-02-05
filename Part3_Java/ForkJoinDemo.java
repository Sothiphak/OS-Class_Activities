package Class_Activity_4.Part3_Java;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Long> {
    private final long threshold = 10;
    private long start;
    private long end;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        // If task is small, compute directly
        if ((end - start) <= threshold) {
            long sum = 0;
            for (long i = start; i <= end; i++) sum += i;
            System.out.println("Calculated partial sum from " + start + " to " + end + 
                               " using " + Thread.currentThread().getName());
            return sum;
        } else {
            // Split task (Fork)
            long mid = (start + end) / 2;
            ForkJoinDemo leftTask = new ForkJoinDemo(start, mid);
            ForkJoinDemo rightTask = new ForkJoinDemo(mid + 1, end);

            leftTask.fork(); // Run asynchronously
            long rightResult = rightTask.compute(); // Compute right synchronously
            long leftResult = leftTask.join(); // Wait for left result

            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinDemo task = new ForkJoinDemo(0, 50);
        long result = pool.invoke(task);
        
        System.out.println("Final Sum: " + result);
    }
}