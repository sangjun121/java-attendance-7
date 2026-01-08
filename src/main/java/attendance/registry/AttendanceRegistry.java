package attendance.registry;

import attendance.controller.dto.AttendanceRequest;
import attendance.domain.Attendance;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRegistry {
    private static final AttendanceRegistry INSTANCE = new AttendanceRegistry();
    private final List<Attendance> attendances;

    private AttendanceRegistry() {
        this.attendances = new ArrayList<>();
    }

    public static AttendanceRegistry getInstance() {
        return INSTANCE;
    }

    public boolean isExistAttendanceByName(LocalDate today, String name) {
        for (Attendance attendance : attendances) {
            if (attendance.getName().equals(name)) {
                return isExistAttendance(attendance, today);
            }
        }
        throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
    }

    public boolean isExistAttendance(Attendance attendance, LocalDate today) {
        for (LocalDateTime attendanceTime : attendance.getAttendances()) {
            if (attendanceTime.toLocalDate().equals(today)) {
                return true;
            }
        }
        return false;
    }

    public void saveAttendance(String name, LocalDateTime attendanceTime){
        findAttendanceByName(name).getAttendances().add(attendanceTime);
    }

    public void initializeAttendances(List<AttendanceRequest> requests) {
        for (AttendanceRequest request : requests) {
            Attendance attendance = findAttendanceByName(request.name());
            updateAttendance(attendance, request.attendanceTime());
        }
    }

    public boolean isExistCrew(String name){
        return !findAttendanceByName(name).getAttendances().isEmpty();
    }

    public Attendance findAttendanceByName(String name) {
        for (Attendance attendance : attendances) {
            if (attendance.getName().equals(name)) {
                return attendance;
            }
        }
        Attendance attendance = new Attendance(name, new ArrayList<>());
        attendances.add(attendance);
        return attendance;
    }

    private void updateAttendance(Attendance attendance, LocalDateTime newAttendanceTime) {
        attendance.getAttendances().add(newAttendanceTime);
    }
}
