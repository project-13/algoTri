package algorithm;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

@MethodName("bitSort4_2")
public class BitSort4_2 implements SortingAlgorithm {

    private static final ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    private static final int SEQUENTIAL_THRESHOLD = 100_000; // Experiment with different values

    @Override
    public void sort(Long[] arr, int order) {
        pool.invoke(new BitSortTask(arr, order, 0, arr.length - 1));
    }

    private static class BitSortTask extends RecursiveAction {
        private final Long[] arr;
        private final int order;
        private final int low;
        private final int high;

        private BitSortTask(Long[] arr, int order, int low, int high) {
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
                sequentialBitSort(arr, order, low, high);
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

            BitSortTask leftTask = new BitSortTask(arr, order - 1, low, right);
            BitSortTask rightTask = new BitSortTask(arr, order - 1, left, high);

            leftTask.fork();
            rightTask.compute();
            leftTask.join();
        }
    }

    private static void sequentialBitSort(Long[] arr, int order, int low, int high) {
        if (order < 0 || low >= high) {
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

        sequentialBitSort(arr, order - 1, low, right);
        sequentialBitSort(arr, order - 1, left, high);
    }
}
