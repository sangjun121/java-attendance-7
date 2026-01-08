package attendance.service;

import attendance.controller.dto.AttendanceRequest;
import attendance.domain.LectureSchedule;
import attendance.domain.Today;
import attendance.registry.AttendanceRegistry;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
        return getStatus(today, attendanceTime);
    }

    private void validateNewDate(LocalDate today, String name) {
        if (attendanceRegistry.isExistAttendanceByName(today, name)) {
            throw new IllegalArgumentException("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
        }
    }

    private String getStatus(Today today, LocalDateTime attendanceTime) {
        LocalTime startTime = LectureSchedule.getLectureScheduleByName(today.getDayOfWeek()).getStartTime();

        if (attendanceTime.toLocalTime().isAfter(startTime.plusMinutes(5))) {
            return "지각";
        }
        if (attendanceTime.toLocalTime().isAfter(startTime.plusMinutes(15))) {
            return "결석";
        }
        return "출석";
    }
}
