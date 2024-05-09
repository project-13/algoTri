package algorithm;

@MethodName("bitSort1")
public class BitSort1 implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr, int order) {
        Long[] tempArray = new Long[arr.length];
        bitSort1(arr, tempArray, order, 0, arr.length - 1);
    }

    public void bitSort1(Long[] arr, Long[] tempArray, int order, int low, int high) {
        if (order < 0 || low >= high) {
            return;
        }

        int left = low;
        int right = high;
        int leftTemp = low;
        int rightTemp = high;

        while (left <= right) {
            while (left <= right && ((arr[left] >> order) & 1) == 0) {
                tempArray[leftTemp++] = arr[left++];
            }

            while (left <= right && ((arr[right] >> order) & 1) == 1) {
                tempArray[rightTemp--] = arr[right--];
            }
        }

        System.arraycopy(tempArray, low, arr, low, rightTemp - low + 1);
        System.arraycopy(arr, left, tempArray, leftTemp, high - left + 1);

        bitSort1(arr, tempArray, order - 1, low, rightTemp);
        bitSort1(arr, tempArray, order - 1, leftTemp, high);
    }

}
