package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Today {
    private final LocalDateTime currentTime;

    public Today() {
        this.currentTime = DateTimes.now();
    }

    public int getYear() {
        return currentTime.getYear();
    }

    public String getMonth() {
        int value = currentTime.getMonth().getValue();
        if (1 <= value && value <= 9) {
            return "0" + value;
        }
        return Integer.toString(value);
    }

    public String getDate() {
        int value = currentTime.getDayOfMonth();
        if (1 <= value && value <= 9) {
            return "0" + value;
        }
        return Integer.toString(value);
    }

    public String getDayOfWeek() {
        return currentTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
    }

    public LocalDate getLocalDate(){
        return currentTime.toLocalDate();
    }
}
