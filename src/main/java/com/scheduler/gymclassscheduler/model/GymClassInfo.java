package com.scheduler.gymclassscheduler.model;

import java.time.LocalTime;

public class GymClassInfo {

    private GymClass gymClass;
    private LocalTime startTime;
    private Integer duration;

    public GymClassInfo(GymClass gymClass, LocalTime startTime, Integer duration) {
        this.gymClass = gymClass;
        this.startTime = startTime;
        this.duration = duration;
    }

    public GymClass getGymClass() {
        return gymClass;
    }

    public void setGymClass(GymClass gymClass) {
        this.gymClass = gymClass;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
