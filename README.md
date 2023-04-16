# Gym class scheduler

## Overview
The following repo contains a scheduler for gym classes defined in classes.json.

## Guidelines

1. Clone this repository.

2. Run the main method in GymClassScheduler.

3. The output of the schedule will be printed in the console.

## Assumptions

1. Each day will always have the same rooms and the same number of rooms.

2. Priority to schedule the classes is given to classes with a longer duration.

3. The next priority is to schedule the classes by the frequency of the gym class.

4. The same gym class can be held consecutively, in the same room or in a different room.

4. 1 trainer can only be in 1 room at any 1 time.

5. The input must always follow the naming 'classes.json' and there can only be 1 input at 1 time. 

## Design
There are 4 models - Room, RoomInfo, GymClass, GymClassInfo.

##### Room
Represents a physical room. A room can have classes in either the first timeslot (AM Session) or second timeslot (PM Session).

##### RoomInfo
Represents each session of a room. It stores the end time of that particular session (13:00 for AM session and 20:00 for PM session). It also keeps track of the remaining duration that session has. Lastly, it stores a list of individual gym class (GymClassInfo) within that session.

##### GymClass
Maps directly from the given input. Contains details about a gym class like the name, trainer, frequency, and type.

##### GymClassInfo
Represents an individual gym class. Contains the GymClass object, the start time of this individual gym class, and its duration.

GymClassScheduler also makes use of multiple util classes like SchedulerUtil to schedule the class.

## Explanation
1. Upon reading the input classes.json file, it gets mapped to a list of GymClass objects.
2. For each GymClass, create individual GymClassInfo based on the frequency of each GymClass, and sort these individual GymClassInfo by their duration in ascending order. This is so that we can prioritise the longest duration first (when we use a stack) to schedule the class.
3. Add the list of individual GymClassInfo to a stack.

4. Next, we'll need to initialise an empty schedule to map the day of the week to the list of rooms available for scheduling. Each room will have two RoomInfo objects, one for the blue room and one for the red room (for now). Each room will have its own AM session and PM session.
5. While the stack is not empty, we take a peek into the stack for the top of the stack (i.e. the one with the longest duration first).
6. Starting with the AM session, if this gym class is valid (i.e. does not clash with other gym classes conducted by the same trainer), we can pop it from the stack and add it into the session.
7. Repeat step 6 for the PM session.
8. Steps 6 and 7 continue for the rest of the weekdays of the map until the stack is empty.
9. Lastly, we format output the gym class schedule. 

 
 


 