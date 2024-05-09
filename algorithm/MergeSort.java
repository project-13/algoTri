package algorithm;

@MethodName("mergeSort")
public class MergeSort implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr, int order) {
        Long[] tempArray = new Long[arr.length];
        mergeSort(arr, tempArray, 0, arr.length - 1);
    }

    private void mergeSort(Long[] arr, Long[] tempArray, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, tempArray, low, mid);
            mergeSort(arr, tempArray, mid + 1, high);
            merge(arr, tempArray, low, mid, high);
        }
    }

    private void merge(Long[] arr, Long[] tempArray, int low, int mid, int high) {
        for (int i = low; i <= high; i++) {
            tempArray[i] = arr[i];
        }

        int leftIndex = low;
        int rightIndex = mid + 1;
        int currentIndex = low;

        while (leftIndex <= mid && rightIndex <= high) {
            if (tempArray[leftIndex] <= tempArray[rightIndex]) {
                arr[currentIndex] = tempArray[leftIndex];
                leftIndex++;
            } else {
                arr[currentIndex] = tempArray[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }

        while (leftIndex <= mid) {
            arr[currentIndex] = tempArray[leftIndex];
            leftIndex++;
            currentIndex++;
        }
    }
}
