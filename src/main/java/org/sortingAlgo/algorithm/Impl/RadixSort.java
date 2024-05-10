package src.main.java.org.sortingAlgo.algorithm.Impl;

import java.util.Arrays;

import src.main.java.org.sortingAlgo.algorithm.SortingAlgorithm;

@MethodName("radixSort")
public class RadixSort implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        // Do counting sort for every byte position
        for (int exp = 0; exp < 8; exp++) {
            countSort(arr, exp);
        }
    }

    // A function to do counting sort of arr[] according to the byte represented by exp.
    private void countSort(Long[] arr, int exp) {
        int n = arr.length;
        Long[] output = new Long[n]; // output array
        int i;
        int[] count = new int[256]; // For 8-bit integers
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++) {
            int byteValue = (int) ((arr[i] >> (exp * 8)) & 0xFF); // Extract byte value
            count[byteValue]++;
        }

        // Change count[i] so that count[i] now contains actual position of this byte in output[]
        for (i = 1; i < 256; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            int byteValue = (int) ((arr[i] >> (exp * 8)) & 0xFF); // Extract byte value
            output[count[byteValue] - 1] = arr[i];
            count[byteValue]--;
        }

        // Copy the output array to arr[], so that arr[] now contains sorted numbers according to current byte
        System.arraycopy(output, 0, arr, 0, n);
    }
}
