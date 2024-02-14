package com.hl.javase.thread.fork_;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author huanglin
 */
public class ForkTest {
    
    static final class SumTask extends RecursiveTask<Integer> {

        final int start;
        final int end;

        SumTask(int start, int end) {
            this.start = start;
            this.end   = end;
        }
        @Override
        protected Integer compute() {
            // 如果计算量小于1000，那么分配一个线程执行if中的代码块，并返回执行结果
            if(end - start < 1000) {
                System.out.println(Thread.currentThread().getName() + " 开始执行：" + start + "-" + end);
                int sum = 0;
                for(int i = start; i <= end; i++) {
                    sum += i;
                }

                return sum;
            }

            // 如果计算量大于1000，那么拆分为两个任务
            SumTask sumTask1 = new SumTask(start, (start + end) / 2);
            SumTask sumTask2 = new SumTask((start + end) / 2, end);

            // 执行任务
            sumTask1.fork();
            sumTask2.fork();

            return sumTask1.join() + sumTask2.join();
        }
    }

    public static void main(String[] args) throws Exception {
        ForkJoinPool     pool = new ForkJoinPool();
        // execute(ForkJoinTask) 异步执行tasks，无返回值
        // invoke(ForkJoinTask) 有Join, tasks会被同步到主进程
        // submit(ForkJoinTask) 异步执行，且带Task返回值，可通过task.get 实现同步到主线程
        SumTask          task = new SumTask(1, 10000);
        pool.submit(task);
        System.out.println(task.get());
    }
}
