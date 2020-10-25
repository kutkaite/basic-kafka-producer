package com.learning.kafkaproducer.service;

import com.learning.kafkaproducer.entity.Commodity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CommodityService {

    private static final Map<String, Commodity> COMMODITY_BASE = new HashMap<>();
    private static final String COPPER = "copper";
    private static final String GOLD = "gold";

    // max adjustment for price (base price * max adjustment)
    private static final double MAX_ADJUSTMENT = 1.05;

    // min adjustment for price (base price * min adjustment)
    private static final double MIN_ADJUSTMENT = 0.95;

    static {
        final long timestamp = System.currentTimeMillis();

        COMMODITY_BASE.put(COPPER, new Commodity(COPPER, 1_300.25, "ounce", timestamp));
        COMMODITY_BASE.put(GOLD, new Commodity(GOLD, 5_500.56, "tonne", timestamp));
    }

    public Commodity createDummyCommodity(String name) {
        if (!COMMODITY_BASE.containsKey(name)) {
            throw new IllegalArgumentException("Invalid Commodity: " + name);
        }

        final Commodity commodity = COMMODITY_BASE.get(name);
        final Double basePrice = commodity.getPrice();
        final Double newPrice = basePrice * ThreadLocalRandom.current().nextDouble(MIN_ADJUSTMENT, MAX_ADJUSTMENT);

        commodity.setPrice(newPrice);
        commodity.setTimestamp(System.currentTimeMillis());

        return commodity;
    }

    public List<Commodity> createDummyCommodities() {
        List<Commodity> commodityList = new ArrayList<>();
        COMMODITY_BASE.keySet().forEach(commodity -> commodityList.add(createDummyCommodity(commodity)));

        return commodityList;
    }
}
