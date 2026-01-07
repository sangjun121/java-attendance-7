package attendance.controller;

import attendance.application.AttendanceService;
import attendance.domain.Today;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

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
        runBootStrap(inputView.readAttendance());
        runApplication();
    }

    private void runBootStrap(Map<String, List<LocalDateTime>> attendances) {
        attendanceService.initializeAttendances(attendances);
    }

    private void runApplication() {
        Today today = attendanceService.readCurrentTime();
        outputView.printMainPage(today.getMonth(), today.getDate(), today.getDayOfWeek());

        String functionNumber = inputView.readFunctionNumber();
        while (!functionNumber.equals("Q")) {
            controlFunctions(functionNumber);

            functionNumber = inputView.readFunctionNumber();
        }
    }

    private void controlFunctions(String functionNumber) {
        if (functionNumber.equals("1")) {
            saveAttendance();
        }
    }

    private void saveAttendance() {
        String nickname = inputView.readNickname();
        LocalTime attendanceTime = inputView.readAttendanceTime();
    }
}
