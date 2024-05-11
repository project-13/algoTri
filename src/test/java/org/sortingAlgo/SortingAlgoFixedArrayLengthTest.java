package src.test.java.org.sortingAlgo;

import java.util.concurrent.TimeUnit;

import src.main.java.org.sortingAlgo.algorithm.*;
import src.main.java.org.sortingAlgo.algorithm.Impl.BitSort;
import src.main.java.org.sortingAlgo.algorithm.Impl.BitSort4_1;
import src.main.java.org.sortingAlgo.algorithm.Impl.BitSort4_2;
import src.main.java.org.sortingAlgo.algorithm.Impl.BitSort4_2_2_1;
import src.main.java.org.sortingAlgo.algorithm.Impl.BitSort5;
import src.main.java.org.sortingAlgo.algorithm.Impl.BitSort_stable;
import src.main.java.org.sortingAlgo.algorithm.Impl.HeapSort;
import src.main.java.org.sortingAlgo.algorithm.Impl.IntroSort;
import src.main.java.org.sortingAlgo.algorithm.Impl.JavaSort;
import src.main.java.org.sortingAlgo.algorithm.Impl.MergeSort;
import src.main.java.org.sortingAlgo.algorithm.Impl.PdqSort;
import src.main.java.org.sortingAlgo.algorithm.Impl.QuickSort;
import src.main.java.org.sortingAlgo.algorithm.Impl.QuickSortMutliThreading;
import src.main.java.org.sortingAlgo.algorithm.Impl.RadixSort;
import src.main.java.org.sortingAlgo.algorithm.Impl.RadixSortB;
import src.main.java.org.sortingAlgo.algorithm.Impl.ShearSort;
import src.main.java.org.sortingAlgo.algorithm.Impl.TimSort;
import src.test.java.org.sortingAlgo.helper.TestHelper;

public class SortingAlgoFixedArrayLengthTest {

    final static int DATA_SIZE =    1_000_000;
    final static TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    static SortingAlgorithm[] sortingAlgorithms = {
            new BitSort(), //top
            //new BitSort_stable(),
            // new BitSort1(),
            // new BitSort2(),
            // new BitSort3(),
            // new BitSort4(),
            new BitSort4_1(), //top
            // new BitSort4_1_m(),
            // new BitSort4_1_m_1(),
            // new BitSort4_1_m_1_1(),
            // new BitSort4_1_m_1_1_without(),
            // new BitSort4_1_m_1_optimized(),
            new BitSort4_2(), //top
            //new BitSort4_2_1(),
            //new BitSort4_2_2(),
            new BitSort4_2_2_1(), //top
            //new BitSort4_2_cpp(),
            //new BitSort4_2_1_1(),//false
            //new Sort42radix(),//false
            //new BitSort4_2_1_w(),//false
            //new BitSort5(),
            //new PdqSort(),//false
            //new RadixSort(),
            //new RadixSortB(),
            //new ShearSort(),//false
            //new IntroSort(),
            //new MergeSort(),
            //new TimSort(),
            //new HeapSort(),
            //new JavaSort(),
            new QuickSort(),
            new QuickSortMutliThreading(),
    };

    public static void main(String args[]) {
        TestHelper.loopFixedDataSize(DATA_SIZE, sortingAlgorithms, TIME_UNIT);
    }

}
