package attendance.domain;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Today {
    private final LocalDateTime currentTime;

    public Today() {
        this.currentTime = LocalDateTime.now();
    }

    public int getMonth() {
        return currentTime.getMonth().getValue();
    }

    public int getDate() {
        return currentTime.getDayOfMonth();
    }

    public String getDayOfWeek() {
        return currentTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
    }
}
