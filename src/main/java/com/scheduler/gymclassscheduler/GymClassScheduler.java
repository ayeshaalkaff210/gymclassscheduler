package com.scheduler.gymclassscheduler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scheduler.gymclassscheduler.model.GymClass;
import com.scheduler.gymclassscheduler.model.GymClassInfo;
import com.scheduler.gymclassscheduler.model.Room;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Stack;

import static com.scheduler.gymclassscheduler.utils.FormatUtil.formatSchedule;
import static com.scheduler.gymclassscheduler.utils.SchedulerUtil.*;

public class GymClassScheduler {

    public static void main(String[] args) {

        final ObjectMapper objectMapper = new ObjectMapper();
        List<GymClass> gymClasses = null;
        try {
            gymClasses = objectMapper.readValue(
                    new File("./src/main/resources/classes.json"),
                    new TypeReference<>() {
                    });
        } catch (IOException e) {
            System.out.println("Error - exception occurred!");
            e.printStackTrace();
        }

        assert gymClasses != null;
        List<GymClassInfo> allGymClassInfos = sortAllGymClassesAscendingDuration(gymClasses);
        Stack<GymClassInfo> gymClassesStack = new Stack<>();
        gymClassesStack.addAll(allGymClassInfos);

        LinkedHashMap<DayOfWeek, List<Room>> schedule = initEmptySchedule();
        while (!gymClassesStack.empty()) {
            insertEachGymClass(gymClassesStack, schedule);
        }

        formatSchedule(schedule);
    }
}
