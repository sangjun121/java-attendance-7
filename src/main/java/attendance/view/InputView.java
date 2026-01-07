package attendance.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import attendance.util.ResourceReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class InputView {
    private final InputParser inputParser;

    public InputView(InputParser inputParser) {
        this.inputParser = inputParser;
    }

    public Map<String, List<LocalDateTime>> readAttendance() {
        List<String> lines = ResourceReader.readCSV("src/main/resources/attendances.csv");
        return inputParser.parseAttendances(lines);
    }

    public String readFunctionNumber(){
        String input = readLine();
        return inputParser.parseFunctionNumber(input);
    }
}
