package attendance.controller;

import attendance.view.InputView;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AttendanceController {
    private final InputView inputView;

    public AttendanceController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        Map<String, List<LocalDateTime>> attendances = inputView.readAttendance();

        for (Entry<String, List<LocalDateTime>> attendance : attendances.entrySet()) {
            System.out.println(attendance.getKey() + " " + attendance.getValue().size());
        }
    }
}
