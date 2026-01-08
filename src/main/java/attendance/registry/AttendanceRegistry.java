package attendance.registry;

import attendance.controller.dto.AttendanceRequest;
import attendance.domain.Attendance;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRegistry {
    private static final AttendanceRegistry INSTANCE = new AttendanceRegistry();
    private final List<Attendance> attendances;

    private AttendanceRegistry() {
        this.attendances = new ArrayList<>();
    }

    public static AttendanceRegistry getInstance() {
        return INSTANCE;
    }

    public void initializeAttendances(List<AttendanceRequest> requests) {
        for (AttendanceRequest request : requests) {
            Attendance attendance = findAttendanceByName(request.name());
            updateAttendance(attendance, request.attendanceTime());
        }
    }

    private Attendance findAttendanceByName(String name) {
        for (Attendance attendance : attendances) {
            if (attendance.getName().equals(name)) {
                return attendance;
            }
        }
        Attendance attendance = new Attendance(name, new ArrayList<>());
        attendances.add(attendance);
        return attendance;
    }

    private void updateAttendance(Attendance attendance, LocalDateTime newAttendanceTime) {
        attendance.getAttendances().add(newAttendanceTime);
    }
}
