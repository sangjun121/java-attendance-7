package attendance.domain;

public class Crew {
    private final String name;
    private int attendanceCount;
    private int lateCount;
    private int missCount;
    private String status;

    public Crew(String name) {
        this.name = name;
        this.attendanceCount = 0;
        this.lateCount = 0;
        this.missCount = 0;
        updateStatus();
    }

    public String getName() {
        return name;
    }

    public int getAttendanceCount() {
        return attendanceCount;
    }

    public int getLateCount() {
        return lateCount;
    }

    public int getMissCount() {
        return missCount;
    }

    public void updateCount(int[] status) {
        attendanceCount = status[0];
        lateCount = status[1];
        missCount = status[2];
        updateStatus();
    }

    public String getStatus() {
        return status;
    }

    private void updateStatus() {
        int badPoint = (this.lateCount / 3) + this.missCount;
        if (badPoint > 5) {
            this.status = "제적";
            return;
        }
        if (badPoint >= 3) {
            this.status = "면담";
            return;
        }
        if (badPoint >= 2) {
            this.status = "경고";
            return;
        }
        this.status = "정상";
    }
}
