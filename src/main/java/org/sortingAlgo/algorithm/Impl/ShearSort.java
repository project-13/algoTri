package src.main.java.org.sortingAlgo.algorithm.Impl;

import src.main.java.org.sortingAlgo.algorithm.SortingAlgorithm;

//TODO correction

@MethodName("shearSort")
public class ShearSort implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr) {
        int n = arr.length;
        int row = (int) Math.sqrt(n);
        int col = n / row;

        // Shear sorting
        for (int i = 0; i < row; i++) {
            // Sort each row
            for (int j = 0; j < col - 1; j++) {
                for (int k = 0; k < col - 1 - j; k++) {
                    if (arr[i * col + k] > arr[i * col + k + 1]) {
                        swap(arr, i * col + k, i * col + k + 1);
                    }
                }
            }
        }

        // Transpose the matrix
        Long[] transposed = new Long[n];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                transposed[j * row + i] = arr[i * col + j];
            }
        }

        // Sort the columns
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row - 1; j++) {
                for (int k = 0; k < row - 1 - j; k++) {
                    if (transposed[i * row + k] > transposed[i * row + k + 1]) {
                        swap(transposed, i * row + k, i * row + k + 1);
                    }
                }
            }
        }

        // Transpose back
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                arr[i * col + j] = transposed[j * row + i];
            }
        }
    }

    private void swap(Long[] arr, int i, int j) {
        Long temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
