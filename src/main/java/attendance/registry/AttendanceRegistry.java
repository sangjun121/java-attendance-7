package attendance.registry;

import attendance.controller.dto.AttendanceRequest;
import attendance.domain.Attendance;
import attendance.domain.Crew;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AttendanceRegistry {
    private static final AttendanceRegistry INSTANCE = new AttendanceRegistry();
    private final List<Attendance> attendances;
    private final List<Crew> crews;

    private AttendanceRegistry() {
        this.attendances = new ArrayList<>();
        this.crews = new ArrayList<>();
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

    public void saveAttendance(String name, LocalDateTime attendanceTime) {
        Attendance attendance = findAttendanceByName(name);
        updateAttendance(attendance, attendanceTime);
        updateStatus(findCrewByName(name), attendance);
    }

    public Map<LocalDateTime, LocalDateTime> updateAttendance(String name, LocalDateTime attendanceTime) {
        Attendance attendance = findAttendanceByName(name);
        Map<LocalDateTime, LocalDateTime> updatedTimes = updateAttendance(attendance, attendanceTime);
        updateStatus(findCrewByName(name), attendance);
        return updatedTimes;
    }

    public void initializeAttendances(List<AttendanceRequest> requests) {
        for (AttendanceRequest request : requests) {
            Attendance attendance = findAttendanceByName(request.name());
            updateAttendance(attendance, request.attendanceTime());
        }

        for (Attendance attendance : attendances) {
            Crew crew = new Crew(attendance.getName());
            updateStatus(crew, attendance);
            crews.add(crew);
        }
    }

    public boolean isExistCrew(String name) {
        for (Crew crew : crews) {
            if (crew.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public Attendance findAttendanceByName(String name) {
        for (Attendance attendance : attendances) {
            if (attendance.getName().equals(name)) {
                return attendance;
            }
        }
        Attendance attendance = new Attendance(name);
        attendances.add(attendance);
        return attendance;
    }

    private Map<LocalDateTime, LocalDateTime> updateAttendance(Attendance attendance, LocalDateTime newAttendanceTime) {
        for (LocalDateTime time : attendance.getAttendances()) {
            if (time.toLocalDate().equals(newAttendanceTime.toLocalDate())) {
                attendance.getAttendances().remove(time);
                attendance.getAttendances().add(newAttendanceTime);
                return Map.of(time, newAttendanceTime);
            }
        }
        return null;
    }

    private void updateStatus(Crew crew, Attendance attendance) {
        crew.updateCount(attendance.getAllStatus());
    }

    public Crew findCrewByName(String name) {
        for (Crew crew : crews) {
            if (crew.getName().equals(name)) {
                return crew;
            }
        }
        throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
    }
}
