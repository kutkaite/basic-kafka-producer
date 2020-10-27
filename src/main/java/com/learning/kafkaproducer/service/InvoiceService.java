package com.learning.kafkaproducer.service;

import com.learning.kafkaproducer.entity.Invoice;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class InvoiceService {
    private static int counter = 0;

    public Invoice generateInvoice() {
        counter++;
        String invoiceNumber = "INV-" + counter;
        Double invoiceAmount = ThreadLocalRandom.current().nextDouble(1, 1000);
        return new Invoice(invoiceNumber, invoiceAmount, "EUR");
    }
}
