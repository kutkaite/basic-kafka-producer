package com.learning.kafkaproducer.service;

import com.learning.kafkaproducer.entity.Image;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class ImageService {
    private static int counter = 0;

    public Image generateImage(String imageType) {
        counter++;
        String name = "image-" + counter;
        long size = ThreadLocalRandom.current().nextLong(100, 10_000);
        return new Image(name, size, imageType);
    }
}
