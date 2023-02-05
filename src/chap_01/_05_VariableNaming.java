package chap_01;

public class _05_VariableNaming {
    public static void main(String[] args) {
        //변수 이름 짓는법
        // 1. 저장할 값에 어울리는 이름
        // 2. 밑줄, 문자, 숫자 사용 가능 (단, 공백은 사용불가)
        // 3. 밑줄 또는 문자로 변수 시작 가능
        // 4. 한 단어 또는 2개 이상 단어의 연속
        // 5. 소문자로 시작, 2개 이상 단어 사용시,각 단어의 시작 글자는 대문자(첫 단어는 제외)
        // 6. 예약어 사용 불가 (public, void, int, double, float 등등)
        
        //입국 신고서 (여행)
        String nationality = "대한민국"; //국적
        String firstName = "다혜"; //이름
        String lastName = "김"; //성
        String dateOfBrith = "1991-12-23"; //생년월일
        String residentialAddress = "무슨 호텔"; //체류지
        String purposeOfVisit = "관광"; //입국목적
        String flightNo = "KE657"; //항공편명
        String _flightNo = "KE657"; //항공편명 밑줄 시작
        String flight_no_2 = "KE657"; //밑줄과 숫자 포함
        //String -flight = "KE657"; 하이픈으로는 시작할 수 없다.

        int accompany = 2; //동반 가족 수
        int lenghtOfStay = 5; // 체류기간

        String item1 = "시계";
        String item2 = "가방";
        //String 3item = "전자제품"; 숫자로는 변수 시작할수 없다.

        // 프로그램의 흐름을 위해 사용되는 경우 등 (크게 이름이 중요하지 않을 때)
        int i = 0;
        String s = "";
        String str = "";

        //상수 : 한번 정의되고 나면 값을 절대로 바꿀 수 없는 수 보통, 대문자로 표현
        //String CODE = "KR"; //이렇게 하면 그냥 CODE라는 변수
        final String CODE = "KR"; // 이렇게 하면 CODE라는 상수로 설정
        // CODE = "US"; //이렇게 바꾸려고 해도 이제 바꿀수 없는게 상수
    }
}
