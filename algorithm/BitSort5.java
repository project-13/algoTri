package algorithm;

@MethodName("bitSort5")
public class BitSort5 implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr, int order) {
        bitSort5(arr, order, 0, arr.length - 1);
    }

    public void bitSort5(Long[] arr, int order, int low, int high) {
        if (order < 0 || low >= high) {
            return;
        }

        int left = low;
        int right = high;

        long mask = 1L << order; // Mask to extract the bit at the current order

        // Partitioning step
        while (left <= right) {
            while (left <= right && (arr[left] & mask) == 0) {
                left++;
            }

            while (left <= right && (arr[right] & mask) != 0) {
                right--;
            }

            if (left <= right) {
                long temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        // Perform sorting on both segments
        bitSort5(arr, order - 1, low, right);
        bitSort5(arr, order - 1, left, high);
    }
}
