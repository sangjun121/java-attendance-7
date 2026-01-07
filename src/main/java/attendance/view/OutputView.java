package attendance.view;

public class OutputView {
    private static final String MAIN_PAGE_GUIDE = "오늘은 %s월 %s일 %s요일입니다. 기능을 선택해 주세요.\n1. 출석 확인\n2. 출석 수정\n3. 크루별 출석 기록 확인\n4. 제적 위험자 확인\nQ. 종료\n";

    public void printMainPage(int month, int day, String dayOfWeek) {
        System.out.printf(MAIN_PAGE_GUIDE, month, day, dayOfWeek);
    }
}
