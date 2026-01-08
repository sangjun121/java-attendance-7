package attendance.controller;

import attendance.controller.dto.AttendanceRequest;
import attendance.domain.Today;
import attendance.service.AttendanceService;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.util.List;

public class AttendanceController {
    private final InputView inputView;
    private final OutputView outputView;
    private final AttendanceService attendanceService;

    public AttendanceController(InputView inputView, OutputView outputView, AttendanceService attendanceService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.attendanceService = attendanceService;
    }

    public void run() {
        Today today = bootStrap();
        runMainPage(today);
    }

    private Today bootStrap() {
        List<AttendanceRequest> attendanceRequests = inputView.readAttendancesInput();
        attendanceService.saveAttendances(attendanceRequests);
        return new Today();
    }

    private void runMainPage(Today today) {
        outputView.printMainPage(today.getMonth(), today.getDate(), today.getDayOfWeek());
    }
}
