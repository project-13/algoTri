package algorithm;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

@MethodName("bitSort4_2_2")
public class BitSort4_2_2 implements SortingAlgorithm {

    private static final ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    private static final int SEQUENTIAL_THRESHOLD = 400000; // Adjust as needed

    @Override
    public void sort(Long[] arr, int order) {
        pool.invoke(new BitSortTask(arr, order, 0, arr.length - 1));
    }

    private class BitSortTask extends RecursiveAction {
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
                introSort(arr, order, low, high);
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

            rightTask.fork();
            leftTask.compute();
            rightTask.join();
        }
    }

    private void introSort(Long[] arr, int order, int low, int high) {
        int maxDepth = (int) (2 * Math.log(arr.length) / Math.log(2));
        introsortUtil(arr, order, low, high, maxDepth);
    }

    private void introsortUtil(Long[] arr, int order, int low, int high, int maxDepth) {
        if (high - low <= 16) {
            insertionSort(arr, low, high);
            return;
        }

        if (maxDepth == 0) {
            heapSort(arr, low, high);
            return;
        }

        int pivotIndex = partition(arr, low, high);
        introsortUtil(arr, order, low, pivotIndex, maxDepth - 1);
        introsortUtil(arr, order, pivotIndex + 1, high, maxDepth - 1);
    }

    private void insertionSort(Long[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Long key = arr[i];
            int j = i - 1;

            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    private int partition(Long[] arr, int low, int high) {
        Long pivot = arr[low + (high - low) / 2];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);

            do {
                j--;
            } while (arr[j] > pivot);

            if (i >= j) {
                return j;
            }

            Long temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    private void heapSort(Long[] arr, int low, int high) {
        int n = high - low + 1;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, low, i);
        }

        for (int i = n - 1; i > 0; i--) {
            Long temp = arr[low];
            arr[low] = arr[low + i];
            arr[low + i] = temp;

            heapify(arr, i, low, 0);
        }
    }

    private void heapify(Long[] arr, int n, int low, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[low + left] > arr[low + largest]) {
            largest = left;
        }

        if (right < n && arr[low + right] > arr[low + largest]) {
            largest = right;
        }

        if (largest != i) {
            Long swap = arr[low + i];
            arr[low + i] = arr[low + largest];
            arr[low + largest] = swap;

            heapify(arr, n, low, largest);
        }
    }
}
