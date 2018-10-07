package com.kafka.learning.Lesson8;

/**
 * Created by hadoop on 8/26/18.
 */
public class RecordCounts {

    private int sum = 0;
    public void add(int n) {
        sum = sum + n;
        System.out.println("sum= "+sum);
    }
}
