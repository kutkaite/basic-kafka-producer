package com.learning.kafkaproducer.scheduler;

import com.learning.kafkaproducer.entity.CarLocation;
import com.learning.kafkaproducer.producer.CarLocationProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class CarLocationScheduler {
    private static final Logger LOG = LoggerFactory.getLogger(CarLocationScheduler.class);

    private final CarLocation carOne;
    private final CarLocation carTwo;
    private final CarLocation carThree;

    @Autowired
    private CarLocationProducer carLocationProducer;

    public CarLocationScheduler() {
        long now = System.currentTimeMillis();

        carOne = new CarLocation("car-one", 0, now);
        carTwo = new CarLocation("car-two", 110, now);
        carThree = new CarLocation("car-three", 95, now);
    }

    @Scheduled(fixedRate = 10000)
    public void generateCarLocation() {
        long now = System.currentTimeMillis();

        carOne.setTimestamp(now);
        carTwo.setTimestamp(now);
        carThree.setTimestamp(now);

        carOne.setDistance(carOne.getDistance() + 1);
        carTwo.setDistance(carTwo.getDistance() - 1);
        carThree.setDistance(carThree.getDistance() + 1);

        try {
            carLocationProducer.sendMessage(carOne);
            LOG.info("Sent: {}", carOne);
            carLocationProducer.sendMessage(carTwo);
            LOG.info("Sent: {}", carTwo);
            carLocationProducer.sendMessage(carThree);
            LOG.info("Sent: {}", carThree);
        } catch (Exception e) {
            LOG.warn("Error happened: {}", e);
        }
    }
}
