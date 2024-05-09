package algorithm;

@MethodName("bitSort3")
public class BitSort3 implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr, int order) {
        Long[] auxArray = new Long[arr.length];
        bitSort3(arr, auxArray, order, 0, arr.length - 1);
    }

    public void bitSort3(Long[] arr, Long[] auxArray, int order, int low, int high) {
        if (order < 0 || low >= high) {
            return;
        }

        int left = low;
        int right = high;
        int leftAux = low;
        int rightAux = high;

        while (left <= right) {
            while (left <= right && ((arr[left] >> order) & 1) == 0) {
                auxArray[leftAux++] = arr[left++];
            }

            while (left <= right && ((arr[right] >> order) & 1) == 1) {
                auxArray[rightAux--] = arr[right--];
            }

            // Update left and right pointers after each inner loop
            if (left <= right) {
                left++;
                right--;
            }
        }

        System.arraycopy(auxArray, low, arr, low, rightAux - low + 1);
        System.arraycopy(arr, left, auxArray, leftAux, high - left + 1);

        bitSort3(arr, auxArray, order - 1, low, rightAux);
        bitSort3(arr, auxArray, order - 1, leftAux, high);
    }

}
