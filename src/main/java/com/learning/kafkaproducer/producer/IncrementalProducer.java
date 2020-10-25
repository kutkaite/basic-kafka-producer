package com.learning.kafkaproducer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class IncrementalProducer {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    private int i = 0;
    private static final Logger LOG = LoggerFactory.getLogger(IncrementalProducer.class);

    @Scheduled(fixedRate = 1000)
    public void sendMessage(){
        i++;
        LOG.info("i is " + i);
        kafkaTemplate.send("t_incremental", "Fixed rate " + i);
    }
}
