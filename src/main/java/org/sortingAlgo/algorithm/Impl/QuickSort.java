package src.main.java.org.sortingAlgo.algorithm.Impl;

import src.main.java.org.sortingAlgo.algorithm.SortingAlgorithm;

@MethodName("quickSort")
public class QuickSort implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(Long[] arr, int low, int high) {
        if (low < high) {
            // Partition the array
            int pi = partition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Long[] arr, int low, int high) {
        long pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // Swap arr[i] and arr[j]
                long temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Swap arr[i+1] and arr[high] (or pivot)
        long temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
}
