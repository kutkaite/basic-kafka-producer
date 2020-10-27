package com.learning.kafkaproducer;

import com.learning.kafkaproducer.entity.*;
import com.learning.kafkaproducer.producer.*;
import com.learning.kafkaproducer.service.ImageService;
import com.learning.kafkaproducer.service.InvoiceService;
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

    @Autowired
    private FoodOrderProducer foodOrderProducer;

    @Autowired
    private SimpleNumberProducer simpleNumberProducer;

    @Autowired
    private ImageProducer imageProducer;

    @Autowired
    private ImageService imageService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceProducer invoiceProducer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Invoice invoice = invoiceService.generateInvoice();

            if (i >= 5) {
                invoice.setAmount(-1);
            }

            invoiceProducer.send(invoice);
        }

        Image image1 = imageService.generateImage("jpg");
        Image image2 = imageService.generateImage("svg");
        Image image3 = imageService.generateImage("jpg");

        imageProducer.publish(image1);
        imageProducer.publish(image2);
        imageProducer.publish(image3);

        for (int i = 0; i < 3; i++) {
            SimpleNumber simpleNumber = new SimpleNumber(i);
            simpleNumberProducer.sendMessage(simpleNumber);
        }

        FoodOrder chickenOrder = new FoodOrder("Chicken", 3);
        FoodOrder fishOrder = new FoodOrder("Fish", 10);
        FoodOrder pizzaOrder = new FoodOrder("Pizza", 5);

        foodOrderProducer.send(chickenOrder);
        foodOrderProducer.send(fishOrder);
        foodOrderProducer.send(pizzaOrder);

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
