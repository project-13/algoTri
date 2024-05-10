package src.main.java.org.sortingAlgo.algorithm.Impl;

import src.main.java.org.sortingAlgo.algorithm.SortingAlgorithm;

@MethodName("introSort")
public class IntroSort implements SortingAlgorithm {

    @Override
    @MethodName("introSort")
    public void sort(Long[] arr) {
        int maxDepth = (int) (2 * Math.log(arr.length) / Math.log(2));
        introsortUtil(arr, 0, arr.length - 1, maxDepth);
    }

    private void introsortUtil(Long[] arr, int low, int high, int maxDepth) {
        if (high - low <= 16) {
            insertionSort(arr, low, high);
            return;
        }

        if (maxDepth == 0) {
            heapSort(arr, low, high);
            return;
        }

        int pivotIndex = partition(arr, low, high);
        introsortUtil(arr, low, pivotIndex, maxDepth - 1);
        introsortUtil(arr, pivotIndex + 1, high, maxDepth - 1);
    }

    private static void insertionSort(Long[] arr, int low, int high) {
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
