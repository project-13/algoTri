package src.main.java.org.sortingAlgo.algorithm.Impl;

import src.main.java.org.sortingAlgo.algorithm.SortingAlgorithm;
import src.main.java.org.sortingAlgo.constant.SortingConstants;

//TODO correction
@MethodName("pdqsort")
public class PdqSort implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr) {
        pdqsort(arr, 0, arr.length - 1, SortingConstants.ORDER);
    }

    private void pdqsort(Long[] arr, int left, int right, int order) {
        final int insertionSortThreshold = 24;
        final int nintherThreshold = 128;

        if (right - left < insertionSortThreshold) {
            insertionSort(arr, left, right, order);
            return;
        }

        int size = right - left + 1;
        int s2 = size / 2;

        if (size > nintherThreshold) {
            sort3(arr, left, left + s2, right, order);
            sort3(arr, left + 1, left + (s2 - 1), right - 1, order);
            sort3(arr, left + 2, left + (s2 + 1), right - 2, order);
            sort3(arr, left + (s2 - 1), left + s2, left + (s2 + 1), order);
            swap(arr, left, left + s2);
        } else {
            sort3(arr, left + s2, left, right, order);
        }

        int[] partitionResult = partitionRight(arr, left, right, order);
        int pivotPos = partitionResult[0];
        boolean alreadyPartitioned = partitionResult[1] == 1;

        int lSize = pivotPos - left;
        int rSize = right - (pivotPos + 1);
        boolean highlyUnbalanced = lSize < size / 8 || rSize < size / 8;

        if (highlyUnbalanced) {
            if (--bad_allowed == 0) {
                heapSort(arr, left, right, order);
                return;
            }

            if (lSize >= insertionSortThreshold) {
                swap(arr, left, left + lSize / 4);
                swap(arr, pivotPos - 1, pivotPos - lSize / 4);
                if (lSize > nintherThreshold) {
                    swap(arr, left + 1, left + (lSize / 4 + 1));
                    swap(arr, left + 2, left + (lSize / 4 + 2));
                    swap(arr, pivotPos - 2, pivotPos - (lSize / 4 + 1));
                    swap(arr, pivotPos - 3, pivotPos - (lSize / 4 + 2));
                }
            }
            if (rSize >= insertionSortThreshold) {
                swap(arr, pivotPos + 1, pivotPos + (1 + rSize / 4));
                swap(arr, right - 1, right - rSize / 4);
                if (rSize > nintherThreshold) {
                    swap(arr, pivotPos + 2, pivotPos + (2 + rSize / 4));
                    swap(arr, pivotPos + 3, pivotPos + (3 + rSize / 4));
                    swap(arr, right - 2, right - (1 + rSize / 4));
                    swap(arr, right - 3, right - (2 + rSize / 4));
                }
            }
        } else {
            if (alreadyPartitioned && partialInsertionSort(arr, left, pivotPos, order)
                    && partialInsertionSort(arr, pivotPos + 1, right, order)) return;
        }

        pdqsort(arr, left, pivotPos, order);
        pdqsort(arr, pivotPos + 1, right, order);
    }

    private void insertionSort(Long[] arr, int left, int right, int order) {
        for (int i = left + 1; i <= right; ++i) {
            for (int j = i; j > left && compare(arr[j], arr[j - 1], order) < 0; --j) {
                swap(arr, j, j - 1);
            }
        }
    }

    private void sort3(Long[] arr, int a, int b, int c, int order) {
        sort2(arr, a, b, order);
        sort2(arr, b, c, order);
        sort2(arr, a, b, order);
    }

    private void sort2(Long[] arr, int a, int b, int order) {
        if (compare(arr[b], arr[a], order) < 0) {
            swap(arr, a, b);
        }
    }

    private int[] partitionRight(Long[] arr, int begin, int end, int order) {
        int pivotPos = begin;
        Long pivotValue = arr[begin];

        int alreadyPartitioned = 1;
        for (int i = begin + 1; i <= end; i++) {
            if (compare(arr[i], pivotValue, order) < 0) {
                ++pivotPos;
                swap(arr, i, pivotPos);
                if (arr[i] != arr[pivotPos]) alreadyPartitioned = 0;
            } else if (arr[i] == pivotValue) {
                alreadyPartitioned++;
            }
        }
        swap(arr, begin, pivotPos);

        return new int[]{pivotPos, alreadyPartitioned};
    }

    private boolean partialInsertionSort(Long[] arr, int begin, int end, int order) {
        for (int i = begin + 1; i <= end; ++i) {
            for (int j = i; j > begin && compare(arr[j], arr[j - 1], order) < 0; --j) {
                swap(arr, j, j - 1);
            }
        }
        return true;
    }

    private void heapSort(Long[] arr, int left, int right, int order) {
        for (int i = (right - left + 1) / 2 - 1; i >= 0; i--) {
            siftDown(arr, i, right - left, left, order);
        }
        for (int i = right - left; i > 0; i--) {
            swap(arr, left, left + i);
            siftDown(arr, 0, i - 1, left, order);
        }
    }

    private void siftDown(Long[] arr, int root, int bottom, int offset, int order) {
        while (root * 2 + 1 <= bottom) {
            int child = root * 2 + 1;
            if (child < bottom && compare(arr[offset + child], arr[offset + child + 1], order) < 0) {
                child++;
            }
            if (compare(arr[offset + root], arr[offset + child], order) < 0) {
                swap(arr, offset + root, offset + child);
                root = child;
            } else {
                return;
            }
        }
    }

    private void swap(Long[] arr, int a, int b) {
        Long temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private int compare(Long a, Long b, int order) {
        return order == 0 ? Long.compare(a, b) : Long.compare(b, a);
    }

    private int bad_allowed = 1;
}
