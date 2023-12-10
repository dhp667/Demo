package com.example.demo.test;

import javax.validation.constraints.NotNull;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class ThreadPoolExecutorTest {
    private static final ThreadPoolExecutor BATCH_UPDATE_POOL;
    /*
     aaaaaaa
     */
    private Object object = new Object();

    private Object object2 = new Object();

    public static final ConcurrentHashMap<Integer, Thread> task2Thread = new ConcurrentHashMap<>(16);

    static {
        //初始化线程池
        BlockingQueue<Runnable> threadPoolQueue = new LinkedBlockingQueue<>(1000);
        BATCH_UPDATE_POOL = new ThreadPoolExtend(
                Runtime.getRuntime().availableProcessors(),
                Runtime.getRuntime().availableProcessors(),
                1000,
                TimeUnit.MINUTES,
                threadPoolQueue,
                new ThreadFactory() {
                    private AtomicInteger threadIndex = new AtomicInteger(0);

                    @Override
                    public Thread newThread(@NotNull Runnable r) {
                        return new Thread(r, "batch_spu" + this.threadIndex.incrementAndGet());
                    }
                }, new ThreadPoolExecutor.CallerRunsPolicy());
    }


    public void print1() throws InterruptedException {
        for (; ; ) {
            System.out.print("2");
            LockSupport.park();
        }
    }


    public void print2(String i) throws InterruptedException {
        synchronized (this) {
            for (; ; ) {
                Thread.sleep(1000);
                System.out.print(i);
            }
        }
    }

    public static synchronized void print3() throws InterruptedException {
        for (; ; ) {
            System.out.print("2");
            LockSupport.park();
        }

    }


    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
        CyclicBarrier countDownLatch = new CyclicBarrier(4);
        Semaphore semaphore = new Semaphore(1);
        semaphore.release();
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep(2000 * finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Thread: %s wait lock%n", Thread.currentThread().getName());
                try {
                    countDownLatch.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.printf("Thread: %s get lock%n", Thread.currentThread().getName());

            }).start();
        }

        System.out.printf("main Thread start wait%n");
        countDownLatch.await();
        System.out.println("main thread get lock");


    }
}

class TreadPoolExecutorTestSub extends ThreadPoolExecutorTest {
    @Override
    public void print2(String i) throws InterruptedException {
        super.print2(i);
    }
}

class ThreadPoolExtend extends ThreadPoolExecutor {

    public ThreadPoolExtend(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ThreadPoolExtend(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ThreadPoolExtend(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ThreadPoolExtend(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        synchronized (ThreadPoolExecutorTest.class) {
            super.beforeExecute(t, r);
        }
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        synchronized (ThreadPoolExecutorTest.class) {
            super.afterExecute(r, t);
        }
    }
}

