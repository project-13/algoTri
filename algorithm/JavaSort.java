package algorithm;

import java.util.Arrays;

@MethodName("javaSort")
public class JavaSort implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr, int order) {
        Arrays.sort(arr);
    }
}
