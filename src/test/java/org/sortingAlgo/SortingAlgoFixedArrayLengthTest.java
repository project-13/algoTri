package src.test.java.org.sortingAlgo;

import java.util.concurrent.TimeUnit;

import src.main.java.org.sortingAlgo.algorithm.*;
import src.main.java.org.sortingAlgo.algorithm.Impl.BitSort;
import src.main.java.org.sortingAlgo.algorithm.Impl.BitSort4_1;
import src.main.java.org.sortingAlgo.algorithm.Impl.BitSort4_2;
import src.main.java.org.sortingAlgo.algorithm.Impl.BitSort4_2_2_1;
import src.main.java.org.sortingAlgo.algorithm.Impl.BitSort_stable;
import src.main.java.org.sortingAlgo.algorithm.Impl.JavaSort;
import src.main.java.org.sortingAlgo.algorithm.Impl.QuickSort;
import src.test.java.org.sortingAlgo.helper.TestHelper;

public class SortingAlgoFixedArrayLengthTest {

    final static int DATA_SIZE =    1_000_000;
    final static TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;

    static SortingAlgorithm[] sortingAlgorithms = {
            new BitSort(),
            new BitSort_stable(),
            // new BitSort1(),
            // new BitSort2(),
            // new BitSort3(),
            // new BitSort4(),
            new BitSort4_1(),
            // new BitSort4_1_m(),
            // new BitSort4_1_m_1(),
            // new BitSort4_1_m_1_1(),
            // new BitSort4_1_m_1_1_without(),
            // new BitSort4_1_m_1_optimized(),
            new BitSort4_2(),
            //new BitSort4_2_1(),
            //new BitSort4_2_2(),
            new BitSort4_2_2_1(),
            //new BitSort4_2_cpp(),
            //new BitSort4_2_1_1(),//false
            //new Sort42radix(),//false
            //new BitSort4_2_1_w(),//false
            //new BitSort5(),
            //new PdqSort(),
            //new RadixSort(), //false
            //new RadixSortB(),
            //new RadixSortParalling(),
            //new ShearSort(),
            //new ShearSort1(),
            //new IntroSort(),
            //new MergeSort(),
            //new TimSort(),
            //new HeapSort(),
            new JavaSort(),
            new QuickSort(),
    };

    public static void main(String args[]) {
        TestHelper.loopFixedDataSize(DATA_SIZE, sortingAlgorithms, TIME_UNIT);
    }

}
