package com.learning.kafkaproducer.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.learning.kafkaproducer.entity.Commodity;
import com.learning.kafkaproducer.producer.CommodityProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CommodityScheduler {
    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CommodityProducer commodityProducer;

    @Scheduled(fixedRate = 500)
    public void fetchCommodities() {
        final List<Commodity> commodities = restTemplate.exchange("http://localhost:80/api/commodity/v1/all",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Commodity>>() {
                }).getBody();

        commodities.forEach(commodity -> {
            try {
                commodityProducer.sendMessage(commodity);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
    }
}
