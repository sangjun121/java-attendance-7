package attendance.view;

import attendance.controller.dto.AttendanceRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public List<AttendanceRequest> parseAttendancesInput(List<String> attendanceInputs) {
        List<AttendanceRequest> requests = new ArrayList<>();
        attendanceInputs.removeFirst();
        for (String input : attendanceInputs) {
            String[] nameAndAttendance = input.split(",");
            requests.add(
                    new AttendanceRequest(parseName(nameAndAttendance[0]), parseAttendanceTime(nameAndAttendance[1])));
        }

        return requests;
    }

    public String parseFunctionNumber(String input) {
        validateFunctionNumber(input);
        return input;
    }

    private String parseName(String input) {
        return input;
    }

    private LocalDateTime parseAttendanceTime(String input) {
        String formattedDateTime = input.replace(" ", "T") + ":00";
        try {
            LocalDateTime attendanceTime = LocalDateTime.parse(formattedDateTime);
            validateAttendanceTime(attendanceTime);
            return attendanceTime;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    private void validateAttendanceTime(LocalDateTime attendanceTime) {
        //TODO: 캠퍼스 운영시간이 아닌경우 예외처리
        //TODO: 주말인 경우 예외처리
    }

    private void validateFunctionNumber(String input) {
        if (!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("Q"))) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }
}
