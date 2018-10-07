package com.kafka.learning.Lesson6;

/**
 * Created by hadoop on 8/9/18.
 */
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;
import java.util.UUID;
public class ProducerDemo {

    static private final String TOPIC = "KafkaLearn8";
    static private final String BROKER_LIST = "spark1234:9092,spark1234:19092";


    public static void main(String[] args) throws Exception {
        Producer<String, String> producer = initProducer();
        sendOne(producer, TOPIC);
    }

    private static Producer<String, String> initProducer() {
        Properties props = new Properties();
        props.put("metadata.broker.list", BROKER_LIST);
        // props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("serializer.class", StringEncoder.class.getName());
        props.put("partitioner.class", HashPartitioner.class.getName());
//    props.put("compression.codec", "0");
        props.put("producer.type", "sync");
        props.put("batch.num.messages", "1");
        props.put("queue.buffering.max.messages", "1000000");
        props.put("queue.enqueue.timeout.ms", "20000000");

        ProducerConfig config = new ProducerConfig(props);
        Producer<String, String> producer = new Producer<String, String>(config);
        return producer;
    }

    public static void sendOne(Producer<String, String> producer, String topic) throws InterruptedException {
        boolean sleepFlag = false;

        for (int index = 0; index < 100; index++) {
            producer.send(new KeyedMessage<String, String>(topic, index + "", UUID.randomUUID() + ""));
        }

        /*

        KeyedMessage<String, String> message1 = new KeyedMessage<String, String>(topic, "1", "test 23");
        producer.send(message1);
        if(sleepFlag) Thread.sleep(5000);
        KeyedMessage<String, String> message2 = new KeyedMessage<String, String>(topic, "2", "test 24");
        producer.send(message2);
        if(sleepFlag) Thread.sleep(5000);
        KeyedMessage<String, String> message3 = new KeyedMessage<String, String>(topic, "3", "test 25");
        producer.send(message3);
        if(sleepFlag) Thread.sleep(5000);
        KeyedMessage<String, String> message4 = new KeyedMessage<String, String>(topic, "4", "test 26");
        producer.send(message4);
        if(sleepFlag) Thread.sleep(5000);
        KeyedMessage<String, String> message5 = new KeyedMessage<String, String>(topic, "5", "test 27");
        producer.send(message5);
        if(sleepFlag) Thread.sleep(5000);
        KeyedMessage<String, String> message6 = new KeyedMessage<String, String>(topic, "6", "test 28");
        producer.send(message6);
        if(sleepFlag) Thread.sleep(5000);

        */

        producer.close();
    }

}
