package src.main.java.org.sortingAlgo.algorithm.Impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import src.main.java.org.sortingAlgo.algorithm.SortingAlgorithm;
import src.main.java.org.sortingAlgo.constant.SortingConstants;

@MethodName("shearSort")
public class ShearSort implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr) {
        int n = arr.length;
        int row = Math.max(1, (int) Math.sqrt(n));
        int col = n / row;

        // Sort the rows in parallel
        ExecutorService rowExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < row; i++) {
            int start = i * col;
            rowExecutor.submit(() -> sortRow(arr, SortingConstants.ORDER, start, col));
        }
        rowExecutor.shutdown();
        try {
            rowExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Sort the columns in parallel
        ExecutorService colExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < col; i++) {
            int finalI = i;
            colExecutor.submit(() -> sortCol(arr, SortingConstants.ORDER, finalI, row, col));
        }
        colExecutor.shutdown();
        try {
            colExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void sortRow(Long[] arr, int order, int start, int length) {
        for (int i = start; i < start + length - 1; i++) {
            for (int j = start; j < start + length - i - 1; j++) {
                if ((arr[j] >> order) > (arr[j + 1] >> order)) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    private void sortCol(Long[] arr, int order, int col, int row, int colSize) {
        for (int i = 0; i < row - 1; i++) {
            for (int j = 0; j < row - i - 1; j++) {
                if ((arr[j * colSize + col] >> order) > (arr[(j + 1) * colSize + col] >> order)) {
                    swap(arr, j * colSize + col, (j + 1) * colSize + col);
                }
            }
        }
    }

    private void swap(Long[] arr, int i, int j) {
        Long temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
