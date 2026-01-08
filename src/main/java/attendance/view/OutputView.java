package attendance.view;

import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

public class OutputView {
    private static final String MAIN_PAGE_GUIDE = "오늘은 %s월 %s일 %s요일입니다. 기능을 선택해 주세요.\n1. 출석 확인\n2. 출석 수정\n3. 크루별 출석 기록 확인\n4. 제적 위험자 확인\nQ. 종료\n";
    private static final String SUCCESS_SAVE_GUIDE = "%s월 %s일 %s요일 %s:%s (%s)\n";
    private static final String ATTENDANCE_STATUS_GUIDE = "이번 달 %s의 출석 기록입니다.\n\n";

    public void printMainPage(String month, String date, String dayOfWeek) {
        System.out.printf(MAIN_PAGE_GUIDE, month, date, dayOfWeek);
    }

    public void printSuccessSaveGuide(String month, String date, String dayOfWeek, int hour, int min,
                                      String attendanceState) {
        System.out.printf(SUCCESS_SAVE_GUIDE, month, date, dayOfWeek,
                parseTimeFormat(hour), parseTimeFormat(min), attendanceState);
    }

    public void printAttendancesResult(String name, Map<LocalDateTime, String> attendances) {
        System.out.printf(ATTENDANCE_STATUS_GUIDE, name);

        for (Map.Entry<LocalDateTime, String> attendance : attendances.entrySet()) {
            LocalDateTime time = attendance.getKey();
            String status = attendance.getValue();
            System.out.printf(SUCCESS_SAVE_GUIDE, time.getMonth().getValue(), time.getDayOfMonth(),
                    time.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN),
                    parseTimeFormat(time.getHour()), parseTimeFormat(time.getMinute()), status);
        }
    }

    private String parseTimeFormat(int time) {
        if (0 <= time && time <= 9) {
            return "0" + time;
        }
        return Integer.toString(time);
    }

}
