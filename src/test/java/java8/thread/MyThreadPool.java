package java8.thread;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by zhanghuan on 2020/6/12.
 */
public class MyThreadPool {

    @Test
    public void testThreadPool() {
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(()->{
            System.out.println("");
        });
        LinkedList<Object> objects = Lists.newLinkedList();
        objects.notifyAll();
        objects.removeFirst();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        executor.execute(() -> System.out.println(""));
        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor();
        //threadPoolExecutor.execute();
    }

    @Test
    public void forkJoinThreadTest() {

    }
}
