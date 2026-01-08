package attendance.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import attendance.controller.dto.AttendanceRequest;
import attendance.util.ResourceReader;
import java.util.List;

public class InputView {
    private static final String ATTENDANCE_RESOURCE_PATH = "src/main/resources/attendances.csv";
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
}
