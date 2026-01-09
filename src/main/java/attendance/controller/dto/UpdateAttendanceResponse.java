package attendance.controller.dto;

import java.time.LocalDateTime;

public record UpdateAttendanceResponse(LocalDateTime before, LocalDateTime after, String beforeStatus,
                                       String afterStatus) {
}
