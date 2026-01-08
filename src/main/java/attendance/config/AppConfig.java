package attendance.config;

import attendance.controller.AttendanceController;
import attendance.registry.AttendanceRegistry;
import attendance.service.AttendanceService;
import attendance.view.InputParser;
import attendance.view.InputView;

public class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public InputView inputView() {
        return new InputView(inputParser());
    }

    public InputParser inputParser() {
        return new InputParser();
    }

    public AttendanceService attendanceService() {
        return new AttendanceService(AttendanceRegistry.getInstance());
    }

    public AttendanceController attendanceController() {
        return new AttendanceController(inputView(), attendanceService());
    }
}
