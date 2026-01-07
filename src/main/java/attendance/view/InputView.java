package attendance.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import attendance.util.ResourceReader;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class InputView {
    private static final String NAME_GUIDE = "닉네임을 입력해 주세요.";
    private static final String TIME_GUIDE = "등교 시간을 입력해 주세요.";

    private final InputParser inputParser;

    public InputView(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public Map<String, List<LocalDateTime>> readAttendance() {
        List<String> lines = ResourceReader.readCSV("src/main/resources/attendances.csv");
        return inputParser.parseAttendances(lines);
    }

    public String readFunctionNumber() {
        String input = readLine();
        return inputParser.parseFunctionNumber(input);
    }

    public String readNickname() {
        System.out.println(NAME_GUIDE);
        String input = readLine();
        return inputParser.parseNickname(input);
    }

    public LocalTime readAttendanceTime() {
        System.out.println(TIME_GUIDE);
        String input = readLine();
        return inputParser.parseAttendanceTime(input);
    }
}
