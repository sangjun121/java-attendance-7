package attendance.controller;

import attendance.application.AttendanceService;
import attendance.view.InputView;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class AttendanceController {
    private final InputView inputView;
    private final AttendanceService attendanceService;

    public AttendanceController(InputView inputView, AttendanceService attendanceService) {
        this.inputView = inputView;
        this.attendanceService = attendanceService;
    }

    public void run() {
        runBootStrap(inputView.readAttendance());
    }

    private void runBootStrap(Map<String, List<LocalDateTime>> attendances) {
        attendanceService.initializeAttendances(attendances);
    }
}
