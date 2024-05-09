package helper;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import algorithm.SortingAlgorithm;

public class Helper {
    public static Long[] generateRandomArray(int size) {
        Random random = new Random();
        return IntStream.range(0, size)
                .mapToObj(i -> random.nextLong() & Long.MAX_VALUE)
                .toArray(Long[]::new);
    }

    public static boolean isSorted(Long[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }

    public static void printArray(Long[] arr) {
        int i = 1;
        for (Long value : arr) {
            System.out.println(i++ +" : "+value);
        }
    }

    public static void printAlgorithmName(SortingAlgorithm[] sortingAlgorithms) {
        System.out.print("size");
        for (SortingAlgorithm algorithm : sortingAlgorithms) {
            System.out.print(";" + algorithm.getMethodName());
        }
        System.out.println("");
    }

    public static long measureExecutionTime(Runnable methodToMeasure) {
        long startTime = System.nanoTime();
        methodToMeasure.run();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void printExecutionTime(Runnable methodToMeasure, TimeUnit timeUnit) {
        long executionTime = measureExecutionTime(() -> methodToMeasure.run());
        long elapsedTime = timeUnit.convert(executionTime, TimeUnit.NANOSECONDS);
        System.out.print(";" + elapsedTime);
    }
}
