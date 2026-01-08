package attendance.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import attendance.controller.dto.AttendanceRequest;
import attendance.domain.Today;
import attendance.util.ResourceReader;
import java.time.LocalDateTime;
import java.util.List;

public class InputView {
    private static final String ATTENDANCE_RESOURCE_PATH = "src/main/resources/attendances.csv";
    private static final String NAME_INPUT_GUIDE = "닉네임을 입력해 주세요.";
    private static final String ATTENDANCE_TIME_INPUT_GUIDE = "등교 시간을 입력해 주세요.";
    private final InputParser inputParser;

    public InputView(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public List<AttendanceRequest> readAttendancesInput() {
        List<String> inputs = ResourceReader.readCsv(ATTENDANCE_RESOURCE_PATH);
        return inputParser.parseAttendancesInput(inputs);
    }

    public String readFunctionNumber() {
        String input = readLine();
        return inputParser.parseFunctionNumber(input);
    }

    public String readNickName(){
        System.out.println(NAME_INPUT_GUIDE);
        String input = readLine();
        return inputParser.parseName(input);
    }

    public LocalDateTime readAttendanceTime(Today today){
        System.out.println(ATTENDANCE_TIME_INPUT_GUIDE);
        String input = readLine();
        return inputParser.parseAttendance(today, input);
    }
}
