package src.main.java.org.sortingAlgo.algorithm.Impl;

import src.main.java.org.sortingAlgo.algorithm.SortingAlgorithm;
import src.main.java.org.sortingAlgo.constant.SortingConstants;

@MethodName("bitSort4_1")
public class BitSort4_1 implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr) {
        bitSort4(arr, SortingConstants.ORDER, 0, arr.length - 1);
    }

    public void bitSort4(Long[] arr, int order, int low, int high) {
        if (order < 0 || low >= high) {
            return;
        }

        int left = low;
        int right = high;

        long mask = 1L << order; // Mask to extract the bit at the current order

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

        bitSort4(arr, order - 1, low, right);
        bitSort4(arr, order - 1, left, high);
    }
}
