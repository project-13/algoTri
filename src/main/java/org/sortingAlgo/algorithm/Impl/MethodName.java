package src.main.java.org.sortingAlgo.algorithm.Impl;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MethodName {
    String value();
}
