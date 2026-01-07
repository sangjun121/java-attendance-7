package attendance.config;

import attendance.controller.AttendanceController;
import attendance.view.InputParser;
import attendance.view.InputView;

public class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public InputParser inputParser(){
        return new InputParser();
    }

    public InputView inputView() {
        return new InputView(inputParser());
    }

    public AttendanceController attendanceController(){
        return new AttendanceController(inputView());
    }
}
