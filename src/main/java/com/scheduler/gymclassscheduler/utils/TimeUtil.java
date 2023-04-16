package com.scheduler.gymclassscheduler.utils;

import com.scheduler.gymclassscheduler.model.GymClassInfo;
import com.scheduler.gymclassscheduler.model.RoomInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TimeUtil {

    /**
     * Checks if time of gymClassInfo currClass is valid
     * If there are existing classes by the same trainer for that session
     * currClass is valid if
     * currClass starts on or after any existing classes ends or
     * currClass ends on or before any existing classes starts
     * @param currClass
     * @param session
     * @return
     */
    public static boolean isTimeValid(GymClassInfo currClass, List<RoomInfo> session) {
        List<GymClassInfo> gInfo = new ArrayList<>();
        for (RoomInfo r : session) {
            gInfo.addAll(r.getGymClassInfo()
                    .stream()
                    .filter(x -> x.getGymClass().getTrainer().equals(currClass.getGymClass().getTrainer()))
                    .collect(Collectors.toList()));
        }

        List<GymClassInfo> clashingClasses = gInfo.stream().filter(existingClass -> {
            boolean isStartTimeOnOrAfterCurrEndTime = currClass.getStartTime().compareTo(existingClass.getStartTime().plusMinutes(existingClass.getDuration())) >= 0;
            boolean isEndTimeOnOrBeforeCurrStartTime = currClass.getStartTime().plusMinutes(currClass.getDuration()).compareTo(existingClass.getStartTime()) <= 0;
            return !(isStartTimeOnOrAfterCurrEndTime || isEndTimeOnOrBeforeCurrStartTime);
        }).collect(Collectors.toList());

        return clashingClasses.isEmpty();
    }

}
