package algorithm;

public interface SortingAlgorithm {
    void sort(Long[] arr, int order);

    default String getMethodName() {
        String annotation = this.getClass().getAnnotation(MethodName.class).value();
        if (annotation != null) {
            return annotation;
        }
        return "Unknown";
    }

}