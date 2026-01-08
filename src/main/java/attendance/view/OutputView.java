package attendance.view;

import java.time.LocalTime;

public class OutputView {
    private static final String MAIN_PAGE_GUIDE = "오늘은 %s월 %s일 %s요일입니다. 기능을 선택해 주세요.\n1. 출석 확인\n2. 출석 수정\n3. 크루별 출석 기록 확인\n4. 제적 위험자 확인\nQ. 종료\n";
    private static final String SAVE_SUCCESS_MESSAGE = "%s월 %s일 %s요일 %s:%s (출석)\n";

    public void printMainPage(int month, int day, String dayOfWeek) {
        System.out.printf(MAIN_PAGE_GUIDE, month, day, dayOfWeek);
    }

    public void printAttendanceSaveMessage(int month, int day, String dayOfWeek, LocalTime time) {
        System.out.printf(SAVE_SUCCESS_MESSAGE, month, day, dayOfWeek, formatTime(time.getHour()),
                formatTime(time.getMinute()));
    }

    private String formatTime(int target) {
        if (0 <= target && target <= 9) {
            return "0" + target;
        }
        return Integer.toString(target);
    }
}
