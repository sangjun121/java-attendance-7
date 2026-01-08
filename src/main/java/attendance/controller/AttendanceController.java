package attendance.controller;

import attendance.controller.dto.AttendanceRequest;
import attendance.domain.Today;
import attendance.service.AttendanceService;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDateTime;
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
        String functionNumber = inputView.readFunctionNumber();
        while (!functionNumber.equals("Q")) {
            guideFunction(today, functionNumber);
            outputView.printMainPage(today.getMonth(), today.getDate(), today.getDayOfWeek());
            functionNumber = inputView.readFunctionNumber();
        }
    }

    private void guideFunction(Today today, String functionNumber) {
        if (functionNumber.equals("1")) {
            saveAttendance(today);

        }
    }

    private void saveAttendance(Today today) {
        attendanceService.validateFunctionPossible(today);
        String name = inputView.readNickName();
        LocalDateTime time = inputView.readAttendanceTime(today);
        attendanceService.saveAttendance(today, name, time);
    }
}
