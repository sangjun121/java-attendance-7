package attendance.view;

import attendance.controller.dto.AttendanceRequest;
import attendance.domain.Today;
import attendance.registry.AttendanceRegistry;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class InputParser {
    private final AttendanceRegistry attendanceRegistry;

    public InputParser(AttendanceRegistry attendanceRegistry) {
        this.attendanceRegistry = attendanceRegistry;
    }

    public List<AttendanceRequest> parseAttendancesInput(List<String> attendanceInputs) {
        List<AttendanceRequest> requests = new ArrayList<>();
        attendanceInputs.removeFirst();
        for (String input : attendanceInputs) {
            String[] nameAndAttendance = input.split(",");
            requests.add(
                    new AttendanceRequest(nameAndAttendance[0], parseAttendanceTime(nameAndAttendance[1])));
        }

        return requests;
    }

    public String parseFunctionNumber(String input) {
        validateFunctionNumber(input);
        return input;
    }

    public String parseName(String input) {
        validateName(input);
        return input;
    }

    public LocalDateTime parseAttendance(Today today, String input) {
        try {
            LocalDateTime attendanceTime = LocalDateTime.parse(
                    today.getYear() + "-" + today.getMonth() + "-" + today.getDate() + "T" + input + ":00");
            validateAttendanceTime(attendanceTime);
            return attendanceTime;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    public int parseDay(Today today, String input) {
        try {
            int day = Integer.parseInt(input);
            validateDay(today, day);
            return day;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
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
        //TODO: 캠퍼스 운영시간이 아닌 경우 예외처리
        //TODO: 주말인 경우 예외처리
    }

    private void validateFunctionNumber(String input) {
        if (!(input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("Q"))) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    private void validateName(String input) {
        if (!attendanceRegistry.isExistCrew(input)) {
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
    }

    private void validateDay(Today today, int target) {
        int todayDay = Integer.parseInt(today.getDate());
        int endDay = YearMonth.now().atEndOfMonth().getDayOfMonth();
        if (!(1 <= target && target <= endDay)) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }

        if (todayDay < target) {
            throw new IllegalArgumentException("[ERROR] 아직 수정할 수 없습니다.");
        }
    }
}
