package attendance.controller.dto;

import java.time.LocalDateTime;

public record AttendanceRequest(String name, LocalDateTime attendanceTime) {
}
