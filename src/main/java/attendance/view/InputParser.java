package attendance.view;

import attendance.domain.DayOfWeek;
import attendance.registory.AttendanceRegistry;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputParser {
    private final AttendanceRegistry attendanceRegistry;

    public InputParser(AttendanceRegistry attendanceRegistry) {
        this.attendanceRegistry = attendanceRegistry;
    }

    public Map<String, List<LocalDateTime>> parseAttendances(List<String> input) {
        Map<String, List<LocalDateTime>> attendances = new HashMap<>();

        input.removeFirst();
        for (String attendance : input) {
            String[] nameAndDateTime = attendance.split(",");
            String nickName = nameAndDateTime[0];
            LocalDateTime time = parseDateTime(nameAndDateTime[1]);

            if (attendances.containsKey(nickName)) {
                attendances.get(nickName).add(time);
            } else {
                attendances.put(nickName, new ArrayList<>(List.of(time)));
            }
        }

        return attendances;
    }

    public String parseFunctionNumber(String input) {
        validateFunctionNumber(input.trim());
        return input.trim();
    }

    public String parseNickname(String input) {
        validateNickname(input);
        return input;
    }

    public LocalTime parseAttendanceTime(String input) {
        String[] split = input.split(":");
        try {
            LocalTime attendanceTime = LocalTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            validateAttendanceTime(attendanceTime);
            return attendanceTime;
        } catch (IllegalArgumentException | DateTimeException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
        }
    }

    private LocalDateTime parseDateTime(String input) {
        String formattedInput = input.replace(" ", "T").concat(":00");
        return LocalDateTime.parse(formattedInput);
    }

    private void validateFunctionNumber(String input) {
        if (input.equals("1") || input.equals("2") || input.equals("3") || input.equals("4") || input.equals("Q")) {
            return;
        }
        throw new IllegalArgumentException("[ERROR] 잘못된 형식을 입력하였습니다.");
    }

    private void validateNickname(String nickname) {
        if (!attendanceRegistry.isExistCrew(nickname)) {
            throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
        }
    }

    private void validateAttendanceTime(LocalTime attendanceTime) {
        String dayOfWeek = attendanceRegistry.getToday().getDayOfWeek();
        LocalTime startTime = DayOfWeek.getDayOfWeek(dayOfWeek).getStartTime();

        if(startTime.isAfter(attendanceTime)){
            throw new IllegalArgumentException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
        }
    }
}
