package attendance.config;

import attendance.application.AttendanceService;
import attendance.controller.AttendanceController;
import attendance.registory.AttendanceRegistry;
import attendance.view.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;

public class AppConfig {
    private static final AppConfig INSTANCE = new AppConfig();

    private AppConfig() {
    }

    public static AppConfig getInstance() {
        return INSTANCE;
    }

    public InputParser inputParser(){
        return new InputParser(AttendanceRegistry.getInstance());
    }

    public InputView inputView() {
        return new InputView(inputParser());
    }

    public OutputView outputView(){
        return new OutputView();
    }

    public AttendanceService attendanceService(){
        return new AttendanceService(AttendanceRegistry.getInstance());
    }

    public AttendanceController attendanceController(){
        return new AttendanceController(inputView(), outputView(), attendanceService());
    }
}
