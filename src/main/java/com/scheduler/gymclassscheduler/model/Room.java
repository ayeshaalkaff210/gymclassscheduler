package com.scheduler.gymclassscheduler.model;

public class Room {

    private String name;
    private RoomInfo amSession;
    private RoomInfo pmSession;

    public Room(String name, RoomInfo amSession, RoomInfo pmSession) {
        this.name = name;
        this.amSession = amSession;
        this.pmSession = pmSession;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomInfo getAmSession() {
        return amSession;
    }

    public void setAmSession(RoomInfo amSession) {
        this.amSession = amSession;
    }

    public RoomInfo getPmSession() {
        return pmSession;
    }

    public void setPmSession(RoomInfo pmSession) {
        this.pmSession = pmSession;
    }
}
