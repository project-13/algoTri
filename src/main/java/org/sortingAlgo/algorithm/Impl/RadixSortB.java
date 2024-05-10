package src.main.java.org.sortingAlgo.algorithm.Impl;

import java.util.Arrays;

import src.main.java.org.sortingAlgo.algorithm.SortingAlgorithm;

@MethodName("radixSortB")
public class RadixSortB implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr) {
        if (arr == null || arr.length == 0)
            return;

        // Find the maximum number of digits
        long max = Arrays.stream(arr).max(Long::compare).orElse(0L);
        int maxDigits = countDigits(max);

        // Do counting sort for every digit
        for (int exp = 1; exp <= maxDigits; exp++) {
            countingSort(arr, exp);
        }
    }

    private void countingSort(Long[] arr, int exp) {
        int n = arr.length;
        Long[] output = new Long[n];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (int i = 0; i < n; i++) {
            int digit = getDigit(arr[i], exp);
            count[digit]++;
        }

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            int digit = getDigit(arr[i], exp);
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        System.arraycopy(output, 0, arr, 0, n);
    }

    private int countDigits(long number) {
        if (number == 0)
            return 1;
        int count = 0;
        while (number != 0) {
            count++;
            number /= 10;
        }
        return count;
    }

    private int getDigit(long number, int exp) {
        return (int) ((number / Math.pow(10, exp - 1)) % 10);
    }
}
