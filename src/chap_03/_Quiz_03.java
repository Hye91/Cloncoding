package chap_03;

public class _Quiz_03 {
    public static void main(String[] args) {
        //주민등록번호에서 생년월일 및 성별까지만 출력하는 프로그램

        String person = "901231-1234567"; //주민등록 번호 13자리
        System.out.println(person.substring(0,8)); //0위치부터 8위치 직전까지
        //끝위치 '직전'까지 잘라서 출력함.

        //선생님 풀이
        //주민등록번호 숫자는 계속 바뀌지만 -의 위치는 바뀌지 않음
        //-을 기준으로 2칸뒤의 직전까지 잘라도 생년월일 및 성별까지 출력가능.
        System.out.println(person.substring(0,person.indexOf("-") + 2));

        person = "030708-4567890";
        System.out.println(person.substring(0,person.indexOf("-") + 2));
    }
}
