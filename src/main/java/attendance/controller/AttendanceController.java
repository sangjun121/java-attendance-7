package attendance.controller;

import attendance.controller.dto.AttendanceRequest;
import attendance.controller.dto.StatusResponse;
import attendance.controller.dto.UpdateAttendanceResponse;
import attendance.domain.Today;
import attendance.service.AttendanceService;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDateTime;
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
        if (functionNumber.equals("2")) {
            updateAttendance(today);
        }
        if (functionNumber.equals("3")) {
            readAttendance();
        }
    }

    private void saveAttendance(Today today) {
        attendanceService.validateFunctionPossible(today);
        String name = inputView.readNickName();
        LocalDateTime time = inputView.readAttendanceTime(today);
        String status = attendanceService.saveAttendance(today, name, time);
        outputView.printSuccessSaveGuide(today.getMonth(), today.getDate(), today.getDayOfWeek(), time.getHour(),
                time.getMinute(), status);
    }

    private void updateAttendance(Today today) {
        attendanceService.validateFunctionPossible(today);
        String name = inputView.readUpdateName();
        int day = inputView.readUpdateDay(today);
        LocalDateTime updateTime = inputView.readUpdateAttendanceTime(today, day);
        UpdateAttendanceResponse updateAttendanceResponse = attendanceService.updateAttendance(name, updateTime);
        outputView.printUpdateResult(updateAttendanceResponse);
    }

    private void readAttendance() {
        String name = inputView.readNickName();
        Map<LocalDateTime, String> attendances = attendanceService.readAttendanceByName(name);
        outputView.printAttendancesResult(name, attendances);
        StatusResponse statusResponse = attendanceService.readStatusByName(name);
        outputView.printStatusResult(statusResponse);
    }
}
