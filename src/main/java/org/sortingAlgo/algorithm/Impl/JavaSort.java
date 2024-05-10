package src.main.java.org.sortingAlgo.algorithm.Impl;

import java.util.Arrays;

import src.main.java.org.sortingAlgo.algorithm.SortingAlgorithm;

@MethodName("javaSort")
public class JavaSort implements SortingAlgorithm {

    @Override
    public void sort(Long[] arr) {
        Arrays.sort(arr);
    }
}
