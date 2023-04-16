package com.scheduler.gymclassscheduler.model;

public class GymClass {

    private String name;
    private String type;
    private int frequency;
    private String trainer;

    private GymClass(){}

    public GymClass(String name, String type, int frequency, String trainer){
        this();
        this.name = name;
        this.type = type;
        this.frequency = frequency;
        this.trainer = trainer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

}
