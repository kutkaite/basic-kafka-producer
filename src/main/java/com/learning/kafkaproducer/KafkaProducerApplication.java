package com.learning.kafkaproducer;

import com.learning.kafkaproducer.entity.Employee;
import com.learning.kafkaproducer.producer.EmployeeProducer;
import com.learning.kafkaproducer.producer.KafkaKeyProducer;
import com.learning.kafkaproducer.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDate;

@SpringBootApplication
@EnableScheduling
public class KafkaProducerApplication implements CommandLineRunner {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private KafkaKeyProducer kafkaKeyProducer;

    @Autowired
    private EmployeeProducer employeeProducer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        kafkaProducer.sendMessage("Some message");

        for (int i = 0; i < 30; i++) {
            var key = "key-" + (i % 4);
            var data = "data " + i + " with key " + key;
            kafkaKeyProducer.send(key, data);

            Thread.sleep(500);
        }

        for (int i = 0; i < 5; i++) {
            final Employee employee = new Employee("emp-" + i, "Employee " + i, LocalDate.now());
            employeeProducer.sendMessage(employee);
        }
    }
}