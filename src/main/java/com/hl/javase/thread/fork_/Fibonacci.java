package com.hl.javase.thread.fork_;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import org.checkerframework.checker.units.qual.s;

/**
 * 官方文档--
 */
public class Fibonacci extends RecursiveTask<Integer>{
    final int n;
    Fibonacci(int n) {
        this.n = n;
    }

    protected Integer compute() {
        if (n < 1) {
            return n;
        }

        Fibonacci f1 = new Fibonacci(n -1);
        f1.fork();
        Fibonacci f2 = new Fibonacci(n -2);
        return f2.compute() + f1.join();
    }

    public static void main(String[] args) {
        ForkJoinPool pool  = new ForkJoinPool(4);
        Fibonacci    fi    = new Fibonacci(20);
        long         start = System.currentTimeMillis();
        Integer      ret   = pool.invoke(fi);
        long         end   = System.currentTimeMillis();

        System.out.println("Fork/join sum: " + ret + " in " + (end - start) + "ms.");
    }
}
