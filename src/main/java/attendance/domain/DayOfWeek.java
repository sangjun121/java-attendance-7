package attendance.domain;

import java.time.LocalTime;

public enum DayOfWeek {
    MONDAY("월", LocalTime.of(13, 00)),
    TUESDAY("화", LocalTime.of(8, 00)),
    WEDNESDAY("수", LocalTime.of(8, 00)),
    THURSDAY("목", LocalTime.of(8, 00)),
    FRIDAY("금", LocalTime.of(8, 00)),
    SATURDAY("토", LocalTime.of(23, 59)),
    SUNDAY("일", LocalTime.of(23, 59));

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

    public String getDayOfWeek(){
        return dayOfWeek;
    }

    public LocalTime getStartTime(){
        return startTime;
    }
}
