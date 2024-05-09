package algorithm;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

@MethodName("bitSort4_1_m")
public class BitSort4_1_m implements SortingAlgorithm {

    private static final ForkJoinPool pool = new ForkJoinPool();

    @Override
    public void sort(Long[] arr, int order) {
        Long[] sortedArr = pool.invoke(new BitSortTask(arr, order, 0, arr.length - 1));
        System.arraycopy(sortedArr, 0, arr, 0, arr.length);
    }

    private class BitSortTask extends RecursiveTask<Long[]> {
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
        protected Long[] compute() {
            if (order < 0 || low >= high) {
                return arr;
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
            Long[] rightResult = rightTask.compute();
            Long[] leftResult = leftTask.join();

            merge(arr, leftResult, rightResult, low, right, high);

            return arr;
        }

        private void merge(Long[] arr, Long[] left, Long[] right, int low, int mid, int high) {
            int leftIndex = 0;
            int rightIndex = 0;
            int currentIndex = low;

            while (leftIndex < mid - low + 1 && rightIndex < high - mid) {
                if (left[leftIndex] <= right[rightIndex]) {
                    arr[currentIndex] = left[leftIndex];
                    leftIndex++;
                } else {
                    arr[currentIndex] = right[rightIndex];
                    rightIndex++;
                }
                currentIndex++;
            }

            while (leftIndex < mid - low + 1) {
                arr[currentIndex] = left[leftIndex];
                leftIndex++;
                currentIndex++;
            }

            while (rightIndex < high - mid) {
                arr[currentIndex] = right[rightIndex];
                rightIndex++;
                currentIndex++;
            }
        }
    }
}
