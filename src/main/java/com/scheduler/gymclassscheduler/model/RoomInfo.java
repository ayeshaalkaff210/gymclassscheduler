package com.scheduler.gymclassscheduler.model;

import java.time.LocalTime;
import java.util.List;

public class RoomInfo {

    private LocalTime endTimeOfRoom;
    private Integer remainingDuration;
    private List<GymClassInfo> gymClassInfo;

    public RoomInfo(LocalTime endTimeOfRoom, Integer remainingDuration, List<GymClassInfo> gymClassInfo) {
        this.endTimeOfRoom = endTimeOfRoom;
        this.remainingDuration = remainingDuration;
        this.gymClassInfo = gymClassInfo;
    }

    public LocalTime getEndTimeOfRoom() {
        return endTimeOfRoom;
    }

    public void setEndTimeOfRoom(LocalTime endTimeOfRoom) {
        this.endTimeOfRoom = endTimeOfRoom;
    }

    public int getRemainingDuration() {
        return remainingDuration;
    }

    public void setRemainingDuration(int remainingDuration) {
        this.remainingDuration = remainingDuration;
    }

    public List<GymClassInfo> getGymClassInfo() {
        return gymClassInfo;
    }

    public void setGymClassInfo(List<GymClassInfo> gymClassInfo) {
        this.gymClassInfo = gymClassInfo;
    }
}
