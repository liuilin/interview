package array;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PrimeNumberSolution {

    public static void main(String[] args) {
        Instant start = Instant.now();
        ExecutorService executor = Executors.newFixedThreadPool(20);
        int i = 0;
        int startNumber = 20220222;
        ArrayList<Integer> primeNumbers = new ArrayList<>();
        while (i < 500) {
            startNumber++;
            MyCallable t = new MyCallable(startNumber);
            Future<Integer> s = executor.submit(t);
            try {
                Integer primeNumber = s.get();
                if (primeNumber != 0) {
                    primeNumbers.add(primeNumber);
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
        System.out.println("质数数量：" + primeNumbers.size());
        Instant end = Instant.now();
        System.out.println("total time elapsed:" + Duration.between(start, end));
    }

    public static boolean isPrime(final int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    static class MyCallable implements Callable<Integer> {

        private int number;

        public MyCallable(int number) {
            this.number = number;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        @Override
        public Integer call() {
            Instant start = Instant.now();
            if (isPrime(number)) {
                Instant end = Instant.now();
                System.out.println("质数：" + number + ",线程号：" + Thread.currentThread().getId() + "," + "耗时：" + Duration.between(start, end));
                return number;
            } else {
                return 0;
            }
        }
    }
}