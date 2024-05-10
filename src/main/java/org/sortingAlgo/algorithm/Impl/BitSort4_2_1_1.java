package src.main.java.org.sortingAlgo.algorithm.Impl;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

import src.main.java.org.sortingAlgo.algorithm.SortingAlgorithm;
import src.main.java.org.sortingAlgo.constant.SortingConstants;

@MethodName("bitSort4_2_1_1")
public class BitSort4_2_1_1 implements SortingAlgorithm {

    private static final ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    private static final int SEQUENTIAL_THRESHOLD = 300000; // Adjust as needed

    @Override
    public void sort(Long[] arr) {
        pool.invoke(new RadixSortTask(arr, SortingConstants.ORDER, 0, arr.length - 1));
    }

    private class RadixSortTask extends RecursiveAction {
        private final Long[] arr;
        private final int order;
        private final int low;
        private final int high;

        private RadixSortTask(Long[] arr, int order, int low, int high) {
            this.arr = arr;
            this.order = order;
            this.low = low;
            this.high = high;
        }

        @Override
        protected void compute() {
            if (order < 0 || low >= high) {
                return;
            }

            if (high - low < SEQUENTIAL_THRESHOLD) {
                Arrays.sort(arr, low, high + 1, (a, b) -> {
                    long bitA = (a >> order) & 1;
                    long bitB = (b >> order) & 1;
                    return Long.compare(bitA, bitB);
                });
                return;
            }

            int left = low;
            int right = high;

            long mask = 1L << order;

            while (left <= right) {
                while (left <= right && (arr[left] & mask) == 0) {
                    left++;
                }

                while (left <= right && (arr[right] & mask) != 0) {
                    right--;
                }

                if (left <= right) {
                    Long temp = arr[left];
                    arr[left] = arr[right];
                    arr[right] = temp;
                    left++;
                    right--;
                }
            }

            RadixSortTask leftTask = new RadixSortTask(arr, order - 1, low, right);
            RadixSortTask rightTask = new RadixSortTask(arr, order - 1, left, high);

            rightTask.fork();
            leftTask.compute();
            rightTask.join();
        }
    }
}
