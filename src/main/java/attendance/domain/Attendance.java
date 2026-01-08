package attendance.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Attendance {
    private final String name;
    private final List<LocalDateTime> attendances;

    public Attendance(String name, List<LocalDateTime> attendances) {
        this.name = name;
        this.attendances = attendances;
    }

    public String getName() {
        return name;
    }

    public List<LocalDateTime> getAttendances() {
        return attendances;
    }
}
