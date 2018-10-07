package com.kafka.learning.Lesson2;

/**
 * Created by hadoop on 5/1/18.
 */
import java.util.concurrent.atomic.AtomicLong;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class RandomPartitioner implements Partitioner {

    private static AtomicLong next = new AtomicLong();


    public RandomPartitioner(VerifiableProperties verifiableProperties) {
        System.out.println("Hash Code: " + this.hashCode());
    }

    @Override
    public int partition(Object key, int numPartitions) {

        int Index = new Double(Math.random()*1000).intValue();
        return Index % numPartitions;
    }
}