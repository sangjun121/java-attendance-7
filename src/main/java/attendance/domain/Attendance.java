package attendance.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Attendance {
    private final String nickName;
    private final List<LocalDateTime> attendanceTime;

    public Attendance(String nickName, List<LocalDateTime> attendanceTime) {
        this.nickName = nickName;
        this.attendanceTime = attendanceTime;
    }

    public String getNickName(){
        return nickName;
    }

    public List<LocalDateTime> getAttendanceTime() {
        return attendanceTime;
    }

    public void saveAttendance(LocalDateTime attendanceTime){
        this.attendanceTime.add(attendanceTime);
    }
}
