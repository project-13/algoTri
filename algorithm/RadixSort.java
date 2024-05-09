package algorithm;

import java.util.Arrays;

@MethodName("radixSort")
public class RadixSort implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr, int order) {
        if (arr == null || arr.length == 0)
            return;

        // Find the maximum number to determine the number of digits
        long max = Arrays.stream(arr).max(Long::compare).orElse(0L);

        // Perform counting sort for every digit
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSort(arr, order, exp);
        }
    }

    private void countingSort(Long[] arr, int order, long exp) {
        int n = arr.length;
        Long[] output = new Long[n];
        int[] count = new int[10]; // Counting array for digits 0 to 9

        // Store count of occurrences in count[]
        for (int i = 0; i < n; i++) {
            int digit = (int) ((arr[i] >> order) / exp) % 10;
            count[digit]++;
        }

        // Change count[i] so that count[i] contains the actual
        // position of this digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            int digit = (int) ((arr[i] >> order) / exp) % 10;
            output[count[digit] - 1] = arr[i];
            count[digit]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        System.arraycopy(output, 0, arr, 0, n);
    }
}
