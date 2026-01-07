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
}
