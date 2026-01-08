package attendance.service;

import attendance.controller.dto.AttendanceRequest;
import attendance.domain.Attendance;
import attendance.domain.LectureSchedule;
import attendance.domain.Today;
import attendance.registry.AttendanceRegistry;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class AttendanceService {
    private final AttendanceRegistry attendanceRegistry;

    public AttendanceService(AttendanceRegistry attendanceRegistry) {
        this.attendanceRegistry = attendanceRegistry;
    }

    public void saveAttendances(List<AttendanceRequest> requests) {
        attendanceRegistry.initializeAttendances(requests);
    }

    public void validateFunctionPossible(Today today) {
        if (today.getDayOfWeek().equals("토") || today.getDayOfWeek().equals("일")) {
            throw new IllegalArgumentException(
                    "[ERROR] " + today.getMonth() + "월 " + today.getDate() + "일 " + today.getDayOfWeek()
                            + "요일은 등교일이 아닙니다.");
        }
    }

    public String saveAttendance(Today today, String name, LocalDateTime attendanceTime) {
        validateNewDate(today.getLocalDate(), name);
        attendanceRegistry.saveAttendance(name, attendanceTime);
        return getStatus(attendanceTime);
    }

    public Map<LocalDateTime, String> readAttendanceByName(String name) {
        Attendance attendance = attendanceRegistry.findAttendanceByName(name);
        List<LocalDateTime> attendancesTime = attendance.getAttendances();
        Collections.sort(attendancesTime);
        return makeResult(attendancesTime);
    }

    private void validateNewDate(LocalDate today, String name) {
        if (attendanceRegistry.isExistAttendanceByName(today, name)) {
            throw new IllegalArgumentException("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
        }
    }

    private String getStatus(LocalDateTime attendanceTime) {
        String dayOfWeek = attendanceTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN);
        LocalTime startTime = LectureSchedule.getLectureScheduleByName(dayOfWeek).getStartTime();

        if (attendanceTime.toLocalTime().isAfter(startTime.plusMinutes(5))) {
            return "지각";
        }
        if (attendanceTime.toLocalTime().isAfter(startTime.plusMinutes(15))) {
            return "결석";
        }
        return "출석";
    }

    private Map<LocalDateTime, String> makeResult(List<LocalDateTime> attendancesTimes) {
        LinkedHashMap<LocalDateTime, String> attendancesResult = new LinkedHashMap<>();
        for (LocalDateTime attendanceTime : attendancesTimes) {
            attendancesResult.put(attendanceTime, getStatus(attendanceTime));
        }
        return attendancesResult;
    }
}
