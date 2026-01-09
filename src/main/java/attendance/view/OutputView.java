package attendance.view;

import attendance.controller.dto.StatusResponse;
import attendance.controller.dto.UpdateAttendanceResponse;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.Map;

public class OutputView {
    private static final String MAIN_PAGE_GUIDE = "오늘은 %s월 %s일 %s요일입니다. 기능을 선택해 주세요.\n1. 출석 확인\n2. 출석 수정\n3. 크루별 출석 기록 확인\n4. 제적 위험자 확인\nQ. 종료\n";
    private static final String SUCCESS_SAVE_GUIDE = "%s월 %s일 %s요일 %s:%s (%s)\n";
    private static final String SUCCESS_UPDATE_GUIDE = "%s월 %s일 %s요일 %s:%s (%s) -> %s:%s (%s) 수정 완료!\n";
    private static final String ATTENDANCE_STATUS_GUIDE = "이번 달 %s의 출석 기록입니다.\n\n";
    private static final String ATTENDANCE_STATUS_RESULT_GUIDE = "출석: %s회\n지각: %s회\n결석: %s회\n\n%s 대상자입니다.";


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
            if (time.getHour() == 23 && time.getMinute() == 59) {
                System.out.printf(SUCCESS_SAVE_GUIDE, time.getMonth().getValue(), time.getDayOfMonth(),
                        time.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN),
                        "--", "--", status);
                continue;
            }
            System.out.printf(SUCCESS_SAVE_GUIDE, parseTimeFormat(time.getMonth().getValue()),
                    parseTimeFormat(time.getDayOfMonth()),
                    time.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN),
                    parseTimeFormat(time.getHour()), parseTimeFormat(time.getMinute()), status);

        }
    }

    public void printStatusResult(StatusResponse response) {
        System.out.printf(ATTENDANCE_STATUS_RESULT_GUIDE, response.attendanceCount(), response.lateCount(),
                response.missCount(), response.status());
    }

    public void printUpdateResult(UpdateAttendanceResponse response) {
        System.out.printf(SUCCESS_UPDATE_GUIDE, response.before().getMonth().getValue(),
                parseTimeFormat(response.before().getDayOfMonth()),
                response.before().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.KOREAN),
                parseTimeFormat(response.before().getHour()), parseTimeFormat(response.before().getMinute()),
                response.beforeStatus(),
                parseTimeFormat(response.after().getHour()), parseTimeFormat(response.after().getMinute()),
                response.afterStatus());
    }

    private String parseTimeFormat(int time) {
        if (0 <= time && time <= 9) {
            return "0" + time;
        }
        return Integer.toString(time);
    }

}
