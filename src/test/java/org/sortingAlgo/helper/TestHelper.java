package src.test.java.org.sortingAlgo.helper;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import src.main.java.org.sortingAlgo.algorithm.SortingAlgorithm;

public class TestHelper {
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

    public static void execution(Long[] array, SortingAlgorithm algorithm) {
        execution(array, algorithm, TimeUnit.MILLISECONDS);
    }

    public static void execution(Long[] array, SortingAlgorithm algorithm, TimeUnit timeUnit) {
        printExecutionTime(() -> {
            algorithm.sort(array);
        }, timeUnit);
        
        System.out.print(" " + isSorted(array));
        
    }

    public static void executionWithCopy(Long[] array, SortingAlgorithm algorithm, TimeUnit timeUnit) {
        Long[] arrayCopy = Arrays.stream(array).toArray(Long[]::new);
        execution(arrayCopy, algorithm, timeUnit);
    }

    public static void loopFixedDataSize(int dataSize, SortingAlgorithm[] sortingAlgorithms, TimeUnit timeUnit) {
        loop(dataSize, dataSize, dataSize, 0, sortingAlgorithms, timeUnit);
    }

    public static void loopVariableDataSize(int startValue, int maxValue, int step, SortingAlgorithm[] sortingAlgorithms, TimeUnit timeUnit) {
        loop(null, startValue, maxValue, step, sortingAlgorithms, timeUnit);
    }

    public static void loop(Integer dataSize, int startValue, int maxValue, int step, SortingAlgorithm[] sortingAlgorithms, TimeUnit timeUnit) {
        printAlgorithmName(sortingAlgorithms);
        for (Integer counter = startValue; counter <= maxValue; counter += step) {
            int loopDataSize = (dataSize != null) ? dataSize : counter;
            Long[] randomList = generateRandomArray(loopDataSize);

            Integer firstColumn = (dataSize == null) ? loopDataSize : counter;
            System.out.print(firstColumn);

            for (SortingAlgorithm algorithm : sortingAlgorithms) {
                executionWithCopy(randomList, algorithm, timeUnit);
            }
            System.out.println("");
        }
    }
}
