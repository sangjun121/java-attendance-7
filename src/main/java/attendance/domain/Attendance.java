package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class Attendance {
    private final String name;
    private final List<LocalDateTime> attendances;

    public Attendance(String name) {
        this.name = name;
        this.attendances = initMonthAllDay();
    }

    public String getName() {
        return name;
    }

    public List<LocalDateTime> getAttendances() {
        return attendances;
    }

    public int[] getAllStatus() {
        int[] status = {0, 0, 0};

        for (LocalDateTime time : attendances) {
            String dayOfWeek = time.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
            LocalTime startTime = LectureSchedule.getLectureScheduleByName(dayOfWeek).getStartTime();
            if (time.toLocalTime().isAfter(startTime.plusMinutes(15))) {
                status[2] += 1;
            } else if (time.toLocalTime().isAfter(startTime.plusMinutes(5))) {
                status[1] += 1;
            } else {
                status[0] += 1;
            }
        }

        return status;
    }

    private List<LocalDateTime> initMonthAllDay() {
        LinkedList<LocalDateTime> initAllDay = new LinkedList<>();
        LocalDateTime today = DateTimes.now();
        YearMonth todayMonth = YearMonth.of(today.getYear(), today.getMonth().getValue());

        for (int i = 1; i < today.getDayOfMonth(); i++) {
            if (!todayMonth.atDay(i).getDayOfWeek().equals(DayOfWeek.SATURDAY) && !todayMonth.atDay(i).getDayOfWeek()
                    .equals(DayOfWeek.SUNDAY)) {
                initAllDay.add(LocalDateTime.of(todayMonth.atDay(i), LocalTime.MAX));
            }
        }
        return initAllDay;
    }
}
