package com.scheduler.gymclassscheduler.utils;

import com.scheduler.gymclassscheduler.model.GymClass;
import com.scheduler.gymclassscheduler.model.GymClassInfo;
import com.scheduler.gymclassscheduler.model.Room;
import com.scheduler.gymclassscheduler.model.RoomInfo;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class SchedulerUtilTest {

    @Test
    void initialiseRoom() {
        LinkedHashMap<DayOfWeek, List<Room>> schedule = new LinkedHashMap<>();
        SchedulerUtil.initialiseRoom(DayOfWeek.WEDNESDAY,schedule);
        assertNotNull(schedule.get(DayOfWeek.WEDNESDAY));
        assertEquals(2, schedule.get(DayOfWeek.WEDNESDAY).size());
        assertNull(schedule.get(DayOfWeek.THURSDAY));
    }

    @Test
    void initEmptySchedule() {
        LinkedHashMap<DayOfWeek, List<Room>> res = SchedulerUtil.initEmptySchedule();
        assertNotNull(res);
        assertEquals(5, res.size());
        assertNull(res.get(DayOfWeek.SATURDAY));
    }

    @Test
    void createIndividualGymClass() {
        GymClass g = new GymClass("Superman", "Strength and Conditioning", 8, "Clark Kent");
        List<GymClassInfo> info = SchedulerUtil.createIndividualGymClass(List.of(g));
        assertEquals(8, info.size());
    }

    @Test
    void sortAllGymClassesAscendingDuration() {
        GymClass g = new GymClass("Superman", "Strength and Conditioning", 8, "Clark Kent");
        GymClass g1 = new GymClass("Power Yoga", "Mind and Body", 2, "Annie Kim");
        List<GymClassInfo> res = SchedulerUtil.sortAllGymClassesAscendingDuration(List.of(g, g1));

        assertEquals(10, res.size());
        assertEquals("Superman", res.get(9).getGymClass().getName());
        assertEquals("Superman", res.get(2).getGymClass().getName());
        assertEquals("Power Yoga", res.get(1).getGymClass().getName());
        assertEquals("Power Yoga", res.get(0).getGymClass().getName());
    }

    @Test
    void insertEachGymClass() {
        GymClass g = new GymClass("Superman", "Strength and Conditioning", 3, "Clark Kent");
        GymClass g1 = new GymClass("Power Yoga", "Mind and Body", 3, "Annie Kim");
        List<GymClassInfo> info = SchedulerUtil.sortAllGymClassesAscendingDuration(List.of(g, g1));
        Stack<GymClassInfo> s = new Stack<>();
        s.addAll(info);
        LinkedHashMap<DayOfWeek, List<Room>> scheduleOutput = SchedulerUtil.initEmptySchedule();
        SchedulerUtil.insertEachGymClass(s, scheduleOutput);

        List<Room> m = scheduleOutput.get(DayOfWeek.MONDAY);
        RoomInfo redAm = m.get(0).getAmSession();
        RoomInfo redPm = m.get(0).getPmSession();
        RoomInfo bluePm = m.get(1).getPmSession();
        assertEquals("Superman", redAm.getGymClassInfo().get(0).getGymClass().getName());
        assertEquals("Superman", redAm.getGymClassInfo().get(1).getGymClass().getName());
        assertEquals("Superman", redPm.getGymClassInfo().get(0).getGymClass().getName());

        assertEquals("Power Yoga", redPm.getGymClassInfo().get(1).getGymClass().getName());
        assertEquals("Power Yoga", redPm.getGymClassInfo().get(2).getGymClass().getName());

        assertEquals("Power Yoga", bluePm.getGymClassInfo().get(0).getGymClass().getName());

    }
}