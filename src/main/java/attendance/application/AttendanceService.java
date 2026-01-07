package attendance.application;

import attendance.domain.Today;
import attendance.registory.AttendanceRegistry;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class AttendanceService {
    private final AttendanceRegistry attendanceRegistry;

    public AttendanceService(AttendanceRegistry attendanceRegistry) {
        this.attendanceRegistry = attendanceRegistry;
    }

    public void initializeAttendances(Map<String, List<LocalDateTime>> attendances) {
        attendanceRegistry.initializeAttendances(attendances);
    }

    public Today readCurrentTime(){
        return attendanceRegistry.readToday();
    }
}
