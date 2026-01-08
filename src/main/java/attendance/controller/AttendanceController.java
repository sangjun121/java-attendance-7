package attendance.controller;

import attendance.controller.dto.AttendanceRequest;
import attendance.service.AttendanceService;
import attendance.view.InputView;
import java.util.List;

public class AttendanceController {
    private final InputView inputView;
    private final AttendanceService attendanceService;

    public AttendanceController(InputView inputView, AttendanceService attendanceService) {
        this.inputView = inputView;
        this.attendanceService = attendanceService;
    }

    public void run(){
        bootStrap();
    }

    private void bootStrap(){
        List<AttendanceRequest> attendanceRequests = inputView.readAttendancesInput();
        attendanceService.saveAttendances(attendanceRequests);
    }
}

