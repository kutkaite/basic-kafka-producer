package com.learning.kafkaproducer.entity;

public class Image {
    private String name;
    private long size;
    private String type;

    public Image(String name, long size, String type) {
        this.name = name;
        this.size = size;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public void setType(String type) {
        this.type = type;
    }
}

