package com.learning.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.kafkaproducer.entity.SimpleNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SimpleNumberProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(SimpleNumber simpleNumber) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(simpleNumber);
        kafkaTemplate.send("t_simple_number", json);
    }
}
