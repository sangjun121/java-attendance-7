package attendance.application;

import attendance.domain.Today;
import attendance.registory.AttendanceRegistry;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class AttendanceService {
    private final AttendanceRegistry attendanceRegistry;

    public AttendanceService(AttendanceRegistry attendanceRegistry) {
        this.attendanceRegistry = attendanceRegistry;
    }

    public void initializeAttendances(Map<String, List<LocalDateTime>> attendances) {
        attendanceRegistry.initializeAttendances(attendances);
    }

    public Today readCurrentTime() {
        return attendanceRegistry.getToday();
    }

    public LocalTime saveAttendance(String nickname, LocalTime attendanceTime) {
        if (attendanceRegistry.isExistAttendanceOnToday(nickname)) {
            throw new IllegalArgumentException("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해 주세요.");
        }
        attendanceRegistry.saveAttendance(nickname, attendanceTime);
        return attendanceTime;
    }
}
