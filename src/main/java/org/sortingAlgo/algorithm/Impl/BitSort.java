package src.main.java.org.sortingAlgo.algorithm.Impl;

import src.main.java.org.sortingAlgo.algorithm.SortingAlgorithm;
import src.main.java.org.sortingAlgo.constant.SortingConstants;

@MethodName("bitSort")
public class BitSort implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr) {
        bitSort(arr, SortingConstants.ORDER, 0, arr.length - 1);
    }

    public void bitSort(Long[] arr, int order, int low, int high) {
        if (order < 0 || low >= high) {
            return;
        }

        int left = low;
        int right = high;

        while (left <= right) {
            while (left <= right && ((arr[left] >> order) & 1) == 0) {
                left++;
            }

            while (left <= right && ((arr[right] >> order) & 1) == 1) {
                right--;
            }

            // Update left and right pointers after each inner loop
            if (left <= right) {
                Long temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        bitSort(arr, order - 1, low, right);
        bitSort(arr, order - 1, left, high);
    }
}
