package cn.hycat.service.edu;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.concurrent.*;

/**
 * @author : lzhycat
 * @date : 2022-07-05 10:45
 * 线程池Demo
 */

public class five_Loaf_on_a_job {
    private static final int capacity = 128;
    private static Boolean[] random = new Boolean[capacity];

    public static void main(String[] args) {
        initRandom();
        for(int i = 0; i < 100; i++) {
            SecureRandom secureRandom = new SecureRandom();
            int x = secureRandom.nextInt(100);
            int y = hashIdx(x);
            if(Boolean.TRUE.equals(random[y])) {
                System.out.println(1);
            }
//            System.out.println("第" + i + "次" +  " " + Boolean.TRUE.equals(random[y]));
        }

        System.out.println();
    }

    public static void initRandom() {
        for(int i = 0; i < 1; i++) {
            random[hashIdx(i)] = Boolean.TRUE;
        }
    }

    public static int hashIdx(int val) {
        // 斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
        int HASH_INCREMENT = 0x61c88647;
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (capacity - 1);
    }

    public void ThreadPoolDemo() {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue(10);
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                5,
                10,
                60L,
                TimeUnit.SECONDS,
                queue,
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        for(int i = 0; i < 10; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Instant.now() + " " + Thread.currentThread().getName());
                }
            });
        }
    }
}
