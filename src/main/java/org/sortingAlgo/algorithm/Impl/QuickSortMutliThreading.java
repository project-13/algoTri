package src.main.java.org.sortingAlgo.algorithm.Impl;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import src.main.java.org.sortingAlgo.algorithm.SortingAlgorithm;

@MethodName("QuickSortMutliThreading")
public class QuickSortMutliThreading implements SortingAlgorithm {

    ForkJoinPool pool = ForkJoinPool.commonPool();
    
    @Override
    public void sort(Long[] arr) {
        pool.invoke(new QuickSortTask(0, arr.length - 1, arr));
    }

    private static class QuickSortTask extends RecursiveTask<Void> {
        private final int start;
        private final int end;
        private final Long[] arr;

        QuickSortTask(int start, int end, Long[] arr) {
            this.start = start;
            this.end = end;
            this.arr = arr;
        }

        @Override
        protected Void compute() {
            if (start < end) {
                int p = partition(start, end, arr);
                QuickSortTask left = new QuickSortTask(start, p - 1, arr);
                QuickSortTask right = new QuickSortTask(p + 1, end, arr);
                left.fork();
                right.compute();
                left.join();
            }
            return null;
        }
    }



    private static int partition(int start, int end, Long[] arr) {
        int i = start, j = end;
        int pivoted = new Random().nextInt(j - i) + i;
        Long t = arr[j];
        arr[j] = arr[pivoted];
        arr[pivoted] = t;
        j--;

        while (i <= j) {
            if (arr[i] <= arr[end]) {
                i++;
                continue;
            }
            if (arr[j] >= arr[end]) {
                j--;
                continue;
            }
            t = arr[j];
            arr[j] = arr[i];
            arr[i] = t;
            j--;
            i++;
        }
        t = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = t;
        return j + 1;
    }
}
