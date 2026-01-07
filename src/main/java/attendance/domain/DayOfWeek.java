package attendance.domain;

import java.time.LocalTime;

public enum DayOfWeek {
    MONDAY("월", LocalTime.of(13, 00)),
    TUESDAY("화", LocalTime.of(10, 00)),
    WEDNESDAY("수", LocalTime.of(10, 00)),
    THURSDAY("목", LocalTime.of(10, 00)),
    FRIDAY("금", LocalTime.of(10, 00));

    private final String dayOfWeek;
    private final LocalTime startTime;

    DayOfWeek(String dayOfWeek, LocalTime startTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
    }

    public static DayOfWeek getDayOfWeek(String dayOfWeek) {
        for (DayOfWeek value : DayOfWeek.values()) {
            if (value.dayOfWeek.equals(dayOfWeek)) {
                return value;
            }
        }

        throw new IllegalArgumentException("[ERROR] 올바르지 않은 요일입니다.");
    }

    public LocalTime getStartTime(){
        return startTime;
    }
}
