package com.kafka.learning.Lesson8;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by hadoop on 8/25/18.
 */
public class NewKafkaConsumer2 {

    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer;
        args = new String[] { "localhost:9092,localhost:19092", "KafkaLearn8", "group2", "consumer1" };
        Properties props = new Properties();
        props.put("bootstrap.servers", args[0]);
        props.put("group.id", args[2]);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(args[1]));

/*
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(500);
            consumer.pause(new TopicPartition(args[1],0));
            consumer.pause(new TopicPartition(args[1],1));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(Thread.currentThread().getName() + " consumed " + record.partition() +
                        "th message with offset: " + record.offset());
            }


    }
*/
    }

}
