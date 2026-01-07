package attendance.registory;

import attendance.domain.Today;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceRegistry {
    private static final AttendanceRegistry INSTANCE = new AttendanceRegistry();
    private final Today today;
    private Map<String, List<LocalDateTime>> attendances;

    private AttendanceRegistry() {
        this.today = new Today();
        this.attendances = new HashMap<>();
    }

    public static AttendanceRegistry getInstance() {
        return INSTANCE;
    }

    public void initializeAttendances(Map<String, List<LocalDateTime>> attendances) {
        this.attendances = attendances;
    }

    public void saveOneAttendance(String name, LocalDateTime time) {
        if (this.attendances.containsKey(name)) {
            this.attendances.get(name).add(time);
        } else {
            this.attendances.put(name, new ArrayList<>(List.of(time)));
        }
    }

    public Today readToday(){
        return today;
    }
}
