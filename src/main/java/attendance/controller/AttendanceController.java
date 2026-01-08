package attendance.controller;

import attendance.controller.dto.AttendanceRequest;
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

    public void run(){
        bootStrap();
        runMainPage();
    }

    private void bootStrap(){
        List<AttendanceRequest> attendanceRequests = inputView.readAttendancesInput();
        attendanceService.saveAttendances(attendanceRequests);
    }

    private void runMainPage(){
        outputView.printMainPage();
    }
}

