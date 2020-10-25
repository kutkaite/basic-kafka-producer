package com.learning.kafkaproducer.api;

import com.learning.kafkaproducer.entity.Commodity;
import com.learning.kafkaproducer.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commodity/v1")
public class CommodityApi {

    @Autowired
    private CommodityService commodityService;

    @GetMapping(value = "/all")
    public List<Commodity> generateCommodities() {
        return commodityService.createDummyCommodities();
    }
}
