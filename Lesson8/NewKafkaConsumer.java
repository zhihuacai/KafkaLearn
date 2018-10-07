package com.kafka.learning.Lesson8;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import java.util.Properties;
import java.util.List;
import java.util.Arrays;
/**
 * Created by hadoop on 8/25/18.
 */
public class NewKafkaConsumer implements Runnable{
    public final KafkaConsumer<String, String> consumer;
    RecordCounts rc = new RecordCounts();
    public NewKafkaConsumer(String brokerList, String groupId, String topic) {
        Properties props = new Properties();
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        this.consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));
    }

    @Override
    public void run() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(500);
            for (ConsumerRecord<String, String> record : records) {
                System.out.println(Thread.currentThread().getName() + " consumed " + record.partition() +
                        "th message with offset: " + record.offset());
                rc.add(1);
          }
       }
    }

}
