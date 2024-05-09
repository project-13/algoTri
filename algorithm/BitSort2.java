package algorithm;

@MethodName("bitSort2")
public class BitSort2 implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr, int order) {
        bitSort2(arr, order, 0, arr.length - 1);
    }

    public void bitSort2(Long[] arr, int order, int low, int high) {
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

            if (left <= right) {
                Long temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left++;
                right--;
            }
        }

        bitSort2(arr, order - 1, low, right);
        bitSort2(arr, order - 1, left, high);
    }

}
