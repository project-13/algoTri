package algorithm;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

@MethodName("bitSort4_1_m_1_1_without")
public class BitSort4_1_m_1_1_without implements SortingAlgorithm {

    private static final ForkJoinPool pool = new ForkJoinPool();
    private static final int SEQUENTIAL_THRESHOLD = 10; // Adjust as needed

    @Override
    public void sort(Long[] arr, int order) {
        pool.invoke(new BitSortTask(arr, order, 0, arr.length - 1));
    }

    private class BitSortTask extends RecursiveTask<Void> {
        private final Long[] arr;
        private final int order;
        private final int low;
        private final int high;
        private final BitSort4_1 bitSortAlgorithm = new BitSort4_1();

        private BitSortTask(Long[] arr, int order, int low, int high) {
            this.arr = arr;
            this.order = order;
            this.low = low;
            this.high = high;
        }

        @Override
        protected Void compute() {
            if (order < 0 || low >= high) {
                return null;
            }

            if (high - low < SEQUENTIAL_THRESHOLD) {
                bitSortAlgorithm.bitSort4(arr, order, low, high);
                return null;
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

            return null;
        }
    }
}
