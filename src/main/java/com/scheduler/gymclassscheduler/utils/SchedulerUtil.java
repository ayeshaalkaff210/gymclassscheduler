package com.scheduler.gymclassscheduler.utils;

import com.scheduler.gymclassscheduler.model.GymClass;
import com.scheduler.gymclassscheduler.model.GymClassInfo;
import com.scheduler.gymclassscheduler.model.Room;
import com.scheduler.gymclassscheduler.model.RoomInfo;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.scheduler.gymclassscheduler.utils.TimeUtil.isTimeValid;

public class SchedulerUtil {

    private static HashMap<String, Integer> gymClassDurationMap = gymClassDurationMap();

    private static HashMap<String, Integer> gymClassDurationMap() {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Strength and Conditioning", 45);
        map.put("Mind and Body", 30);
        map.put("Cardio", 60);
        map.put("Dance", 60);
        return map;
    }

    /**
     * Initialise empty rooms for a given day
     * @param day
     * @param schedule
     */
    static void initialiseRoom(DayOfWeek day, LinkedHashMap<DayOfWeek, List<Room>> schedule) {
        Room redRoom = new Room("Red Room", new RoomInfo(LocalTime.of(13, 0), 120, new ArrayList<>()), new RoomInfo(LocalTime.of(20, 0), 120, new ArrayList<>()));
        Room blueRoom = new Room("Blue Room", new RoomInfo(LocalTime.of(13, 0),120, new ArrayList<>()), new RoomInfo(LocalTime.of(20, 0), 120, new ArrayList<>()));
        schedule.put(day, List.of(redRoom, blueRoom));
    }

    /**
     * Initialise an empty schedule with days of the week and the available rooms
     * @return initialised empty schedule
     */
    public static LinkedHashMap<DayOfWeek, List<Room>> initEmptySchedule() {
        LinkedHashMap<DayOfWeek, List<Room>> schedule = new LinkedHashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            if (!(day.getValue() == 6 || day.getValue() == 7)) {
                initialiseRoom(day, schedule);
            }
        }
        return schedule;
    }

    /**
     * Given a list of gymClass with frequency, create individual gym classes
     * @param gymClass
     * @return
     */
    static List<GymClassInfo> createIndividualGymClass(List<GymClass> gymClass) {

        List<GymClassInfo> output = new ArrayList<>();
        for (GymClass g : gymClass) {
            for (int i = 0; i < g.getFrequency(); i++) {
                output.add(new GymClassInfo(g, null, gymClassDurationMap.get(g.getType())));
            }
        }
        return output;
    }

    /**
     * Sort all gym classes by ascending duration
     * @param gymClasses
     * @return
     */
    public static List<GymClassInfo> sortAllGymClassesAscendingDuration(List<GymClass> gymClasses) {

        List<GymClass> sortedFreq = gymClasses.stream()
                .sorted(Comparator.comparingInt(g -> gymClassDurationMap.get(g.getType())))
                .collect(Collectors.toList());

        return createIndividualGymClass(sortedFreq);
    }


    /**
     * Insert each gym class into the schedule
     * @param gymClassInfoStack
     * @param schedule
     */
    public static void insertEachGymClass(Stack<GymClassInfo> gymClassInfoStack, LinkedHashMap<DayOfWeek, List<Room>> schedule) {

        for (Map.Entry<DayOfWeek, List<Room>> entry : schedule.entrySet()) {

            List<RoomInfo> amRoomSessions = entry.getValue().stream().map(Room::getAmSession).collect(Collectors.toList());
            List<RoomInfo> pmRoomSessions = entry.getValue().stream().map(Room::getPmSession).collect(Collectors.toList());

            for (RoomInfo r : amRoomSessions) {
                insertEachGymClass(gymClassInfoStack, r, amRoomSessions);
            }

            for (RoomInfo r : pmRoomSessions) {
                insertEachGymClass(gymClassInfoStack, r, pmRoomSessions);
            }
        }
    }

    /**
     * Insert each gym class from the stack, after checking if time is valid to be inserted
     * @param gymClassInfoStack
     * @param r
     * @param session
     */
    private static void insertEachGymClass(Stack<GymClassInfo> gymClassInfoStack, RoomInfo r, List<RoomInfo> session) {
        while (!gymClassInfoStack.empty() && gymClassInfoStack.peek().getDuration() <= r.getRemainingDuration()) {
            GymClassInfo g = gymClassInfoStack.peek();
            g.setStartTime(r.getEndTimeOfRoom().minusMinutes(r.getRemainingDuration()));

            if (isTimeValid(g, session)) {
                gymClassInfoStack.pop();
                r.setRemainingDuration(r.getRemainingDuration() - g.getDuration());
                r.getGymClassInfo().add(g);
                r.setGymClassInfo(r.getGymClassInfo());
            } else {
                g.setStartTime(null);
                break;
            }
        }
    }
}
