package com.learning.kafkaproducer.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.kafkaproducer.entity.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void send(Invoice invoice) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(invoice);
        kafkaTemplate.send("t_invoice", invoice.getNumber(), json);
    }
}
