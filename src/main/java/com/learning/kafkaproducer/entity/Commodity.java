package com.learning.kafkaproducer.entity;

public class Commodity {
    private String name;
    private Double price;
    private String measurement;
    private long timestamp;

    public Commodity() {
    }

    public Commodity(String name, Double price, String measurement, long timestamp) {
        this.name = name;
        setPrice(price);
        this.measurement = measurement;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public String getMeasurement() {
        return measurement;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = Math.round(price * 100d) / 100d;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", measurement='" + measurement + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
