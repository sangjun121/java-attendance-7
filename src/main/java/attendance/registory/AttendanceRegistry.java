package attendance.registory;

import attendance.domain.Attendance;
import attendance.domain.Today;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class AttendanceRegistry {
    private static final AttendanceRegistry INSTANCE = new AttendanceRegistry();
    private final Today today;
    private List<Attendance> attendances;

    private AttendanceRegistry() {
        this.today = new Today();
        this.attendances = new ArrayList<>();
    }

    public static AttendanceRegistry getInstance() {
        return INSTANCE;
    }

    public void initializeAttendances(Map<String, List<LocalDateTime>> attendancesInput) {
        List<Attendance> attendances = new ArrayList<>();
        for (Entry<String, List<LocalDateTime>> entry : attendancesInput.entrySet()) {
            Attendance attendance = new Attendance(entry.getKey(), entry.getValue());
            attendances.add(attendance);
        }

        this.attendances = attendances;
    }

    public Today getToday() {
        return today;
    }

    public boolean isExistCrew(String nickName){
        for (Attendance attendance : attendances){
            if(attendance.getNickName().equals(nickName)){
                return true;
            }
        }

        return false;
    }
}
