package src.main.java.org.sortingAlgo.algorithm;

import src.main.java.org.sortingAlgo.algorithm.Impl.MethodName;

public interface SortingAlgorithm {
    void sort(Long[] arr);

    default String getMethodName() {
        MethodName methodName = this.getClass().getAnnotation(MethodName.class);
        return (methodName != null) ? methodName.value() : this.getClass().getName();
    }

}