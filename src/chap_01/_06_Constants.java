package chap_01;

public class _06_Constants {
    public static void main(String[] args) {
        final String KR_COUNTRY_CODE =  "+82"; //국가번호 (빨리)
        //final 이라는 키워드 (작업 프로그램에서 약속된 단어) :뒤의 값은 변수가 아닌 상수
        //KR_COUNTRY_CODE = "+8282"; 변수가 아닌 상수가 되었기 때문에 오류가 됨.
        System.out.println(KR_COUNTRY_CODE);

        final double PI = 31.41592; //원주율
        final String DATE_OF_BIRTH = "2001-12-31"; //생년 월일
        //상수의 설정시 전부 대문자로 구성되고 2개 이상의 단어는 밑줄로 구분

    }
}
