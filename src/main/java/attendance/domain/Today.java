package attendance.domain;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Today {
    private final LocalDateTime currentTime;
    private final DayOfWeek dayOfWeek;

    public Today() {
        this.currentTime = DateTimes.now();
        this.dayOfWeek = DayOfWeek.getDayOfWeek(
                currentTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN));
    }
}
