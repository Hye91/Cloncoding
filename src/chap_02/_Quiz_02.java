package chap_02;

public class _Quiz_02 {
    public static void main(String[] args) {
        //조건
        // 키가 120cm 이상인 경우에만 탑승 가능
        // 삼항 연산자 이용

        //실행결과
        // 키가 115cm 이므로 탑승 불가능합니다.
        // 키가 121cm 이므로 탑승 가능합니다.

        int a = 120;
        int b = 115;
        int c = 121;

        String height = (b > a) ? "불가능" : "가능";
        String height2 = (c > a) ? "불가능" : "가능";

        System.out.println("키가 " + b + "cm 이므로 " + height + "합니다");
        System.out.println("키가 " + c + "cm 이므로 " + height2 + "합니다");

        //선생님 버전
        int height3 = 115;
        String result = (height3 >= 120) ? "탑승 가능합니다" : "탑승 불가능합니다";
        System.out.println("키가 " + height3 + "cm 이므로 " + result);

        //나와의 차이점.
        //120이라는 수는 고정값이므로 굳이 변수로 지정해주지 않았다.
        //변수의 갯수가 줄어드니까 식이 좀 더 깔끔해짐.넣어야할 변수값이 줄어서 sout 할때에도 쳐야할 문장이 줄어들었다.
        //바로 height라는 변수값을 정할 수 있어서 식의 가독성이 좋다.
    }
}
