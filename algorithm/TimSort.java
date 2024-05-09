package algorithm;

@MethodName("timSort")
public class TimSort implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr, int order) {
        timSort(arr, 0, arr.length - 1);
    }

    private void timSort(Long[] arr, int low, int high) {
        if (high - low < 64) {
            insertionSort(arr, low, high);
            return;
        }

        int mid = (low + high) / 2;
        timSort(arr, low, mid);
        timSort(arr, mid + 1, high);
        merge(arr, low, mid, high);
    }

    private void insertionSort(Long[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Long key = arr[i];
            int j = i - 1;

            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    private void merge(Long[] arr, int low, int mid, int high) {
        int leftSize = mid - low + 1;
        int rightSize = high - mid;

        Long[] leftArray = new Long[leftSize];
        Long[] rightArray = new Long[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = arr[low + i];
        }

        for (int i = 0; i < rightSize; i++) {
            rightArray[i] = arr[mid + 1 + i];
        }

        int leftIndex = 0;
        int rightIndex = 0;
        int currentIndex = low;

        while (leftIndex < leftSize && rightIndex < rightSize) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                arr[currentIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                arr[currentIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            currentIndex++;
        }

        while (leftIndex < leftSize) {
            arr[currentIndex] = leftArray[leftIndex];
            leftIndex++;
            currentIndex++;
        }

        while (rightIndex < rightSize) {
            arr[currentIndex] = rightArray[rightIndex];
            rightIndex++;
            currentIndex++;
        }
    }
}
