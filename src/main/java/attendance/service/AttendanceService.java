package attendance.service;

import attendance.controller.dto.AttendanceRequest;
import attendance.registry.AttendanceRegistry;
import java.util.List;

public class AttendanceService {
    private final AttendanceRegistry attendanceRegistry;

    public AttendanceService(AttendanceRegistry attendanceRegistry) {
        this.attendanceRegistry = attendanceRegistry;
    }

    public void saveAttendances(List<AttendanceRequest> requests) {
        attendanceRegistry.initializeAttendances(requests);
    }
}
