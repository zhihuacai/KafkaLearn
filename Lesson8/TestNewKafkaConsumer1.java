package com.kafka.learning.Lesson8;

/**
 * Created by hadoop on 8/26/18.
 */
public class TestNewKafkaConsumer1 {

    public static void main (String [] args) {
        NewKafkaConsumer consumer = new NewKafkaConsumer("localhost:9092","group1" , "KafkaLearn7");
        new Thread(consumer).start();
        new Thread(consumer).start();
    }
}
