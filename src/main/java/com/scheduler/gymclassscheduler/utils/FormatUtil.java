package com.scheduler.gymclassscheduler.utils;

import com.scheduler.gymclassscheduler.model.GymClass;
import com.scheduler.gymclassscheduler.model.GymClassInfo;
import com.scheduler.gymclassscheduler.model.Room;

import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FormatUtil {

    public static void formatSchedule(LinkedHashMap<DayOfWeek, List<Room>> schedule) {

        System.out.printf("--------------------------------%n");
        System.out.printf("    Gym Class Scheduler      %n");
        System.out.printf("--------------------------------%n");

        for (Map.Entry<DayOfWeek, List<Room>> value : schedule.entrySet()) {

            System.out.printf("| %-3s |", value.getKey().toString().toUpperCase().substring(0, 3));
            System.out.println();
            for (Room r : value.getValue()) {
                System.out.printf("%n[ROOM : %s]%n%n" , r.getName());

                System.out.printf("%50s %n", "Lunch time classes (11:00 - 13:00)");
                for (GymClassInfo g : r.getAmSession().getGymClassInfo()) {
                    GymClass gymClass = g.getGymClass();
                    System.out.printf("%-8s %-15s %-30s %-7s %n", g.getStartTime(), gymClass.getName(), gymClass.getType(), gymClass.getTrainer());
                }
                System.out.printf("%n %53s %n", "Post-office hours classes (18:00 - 20:00)");
                for (GymClassInfo g : r.getPmSession().getGymClassInfo()) {
                    GymClass gymClass = g.getGymClass();
                    System.out.printf("%-8s %-15s %-30s %-7s %n", g.getStartTime(), gymClass.getName(), gymClass.getType(), gymClass.getTrainer());
                }
            }

            System.out.println("\n");
        }
    }

}
