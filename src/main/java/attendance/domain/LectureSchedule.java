package attendance.domain;

import java.time.LocalTime;

public enum LectureSchedule {
    MONDAY("월", LocalTime.of(13, 00), LocalTime.of(23, 00)),
    TUESDAY("화", LocalTime.of(10, 00), LocalTime.of(23, 00)),
    WEDNESDAY("수", LocalTime.of(10, 00), LocalTime.of(23, 00)),
    THURSDAY("목", LocalTime.of(10, 00), LocalTime.of(23, 00)),
    FRIDAY("금", LocalTime.of(10, 00), LocalTime.of(23, 00));

    private final String name;
    private final LocalTime startTime;
    private final LocalTime endTime;

    LectureSchedule(String name, LocalTime startTime, LocalTime endTime) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static LectureSchedule getLectureScheduleByName(String name) {
        for (LectureSchedule lectureSchedule : LectureSchedule.values()) {
            if (lectureSchedule.getName().equals(name)) {
                return lectureSchedule;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
