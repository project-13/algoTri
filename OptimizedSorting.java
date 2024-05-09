import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import algorithm.*;
import helper.Helper;

public class OptimizedSorting {

    final static int DATA_SIZE =    500_000_000;
    final static int START_VALUE = 10_000_000;
    final static int MAX_VALUE =   500_000_000;
    final static int STEP =         10_000_000;

    OptimizedSorting() {
        super();
    }

    static SortingAlgorithm[] sortingAlgorithms = {
            new BitSort(),
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
            //new RadixSortParalling(),
            //new ShearSort(),
            //new ShearSort1(),
            //new IntroSort(),
            //new MergeSort(),
            //new TimSort(),
            //new HeapSort(),
            new JavaSort(),
            new QuickSort(), //ffalse
    };

    public static void main(String args[]) {

        Helper.printAlgorithmName(sortingAlgorithms);
        loopVariableDataSize();
        // Helper.printAlgorithmName(sortingAlgorithms);
        //loopFixedDataSize();
    }

    public static void execution(Long[] array, SortingAlgorithm algorithm) {
        execution(array, algorithm, TimeUnit.MILLISECONDS);
    }

    public static void execution(Long[] array, SortingAlgorithm algorithm, TimeUnit timeUnit) {
        Helper.printExecutionTime(() -> {
            algorithm.sort(array, 63);
        }, timeUnit);
        
        System.out.print(" " + Helper.isSorted(array));
        
    }

    public static void executionWithCopy(Long[] array, SortingAlgorithm algorithm) {
        Long[] arrayCopy = Arrays.stream(array).toArray(Long[]::new);
        execution(arrayCopy, algorithm);
    }

    public static void loopFixedDataSize() {
        loopFixedDataSize(DATA_SIZE);
    }

    public static void loopFixedDataSize(int dataSize) {
        loop(dataSize, START_VALUE, MAX_VALUE, STEP);
    }

    public static void loopVariableDataSize() {
        loopVariableDataSize(0, MAX_VALUE, 1);
    }

    public static void loopVariableDataSize(int maxValue) {
        loopVariableDataSize(0, MAX_VALUE, 1);
    }

    public static void loopVariableDataSize(int startValue, int maxValue, int step) {
        loop(null, startValue, maxValue, step);
    }

    public static void loop(Integer dataSize, int startValue, int maxValue, int step) {
        for (Integer counter = START_VALUE; counter <= MAX_VALUE; counter += STEP) {
            int loopDataSize = (dataSize != null) ? dataSize : counter;
            Long[] randomList = Helper.generateRandomArray(loopDataSize);

            Integer firstColumn = (dataSize == null) ? loopDataSize : counter;
            System.out.print(firstColumn);

            for (SortingAlgorithm algorithm : sortingAlgorithms) {
                executionWithCopy(randomList, algorithm);
            }
            System.out.println("");
        }
    }

}
