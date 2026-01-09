package attendance.controller.dto;

public record StatusResponse(int attendanceCount, int lateCount, int missCount, String status) {
}
