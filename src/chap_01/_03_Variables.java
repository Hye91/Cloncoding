package chap_01;

public class _03_Variables {
    public static void main(String[] args) {
        String name; //name이라는 문자열 변수가 설정된것
        name = "다혜"; //프로그래밍에서 =은 오른쪽에 있는 값을 왼쪽의 객체(변수)에 넣겠다는 뜻
        //변수 : 어떤 데이터, 값을 저장하는 공간
        int hour = 15; //hour이라는 정수형 변수가 설정된것
        //배송문자의 경우 배송자의 이름을 그때그때 매번 설정을 하긴 어렵고 귀찮음.
        //배송자의 이름을 변수로 빼서 저장해놓고 저장된 데이터를 출력
        System.out.println("다혜님, 배송이 시작됩니다. 15시에 방문 예정입니다.");
        System.out.println("다혜님, 배송이 완료되었습니다.");
        System.out.println(name +"님, 배송이 시작됩니다. " + hour + "시에 방문 예정입니다.");
        System.out.println(name + "님, 배송이 완료되었습니다.");
        
        //String name = "다혜"; 라고 한번에 변수를 설정하는 것도 가능

        double score = 90.5; //실수 자료형
        char grade = 'A'; //여러글자의 경우 string, 한문자 또는 글자의 경우 char 사용가능
        name = "강백호"; //변수의 값을 바꿀수 있다. name이라는 변수는 그대로 사용을 하되,
        //변수의 값을 강백호로 바꿔서 집어넣겠다는 뜻. 이 밑으로는 이제 변수가 강백호의 값. 값의 업데이트 가능
        System.out.println(name + "님의 평균 점수는" + score + "점입니다.");
        System.out.println("학점은 " + grade + "입니다.");

        boolean pass = true;//불리안 변수
        System.out.println("이번 시험에 합격했을까요?" + pass );

        //double 자료형은 상대적으로 아주 정밀한 데이터를 표현하기 적합
        //double 보다는 정밀도가 떨어지지만 실수값을 저장할수 있는 float도 있다.

        double d = 3.14123456789;
        //float f = 3.14; 오류 발생, 소수점포험하는 실수값은 기본적으로 더블자료로 인식
        //플롯은 더블자료만큼 큰, 또는 정밀한 자료를 넣을수 없기때문에 에러가 뜬다.
        float f = 3.14123456789F; //그러므로 플롯 자료로 넣어주기 위해서는 값의 뒤에 f 또는 F를
        //입력해주어야 플롯 자료로 인식을 할수 있게 된다.
        System.out.println(d);
        System.out.println(f);

        //int i = 1000000000000 int의 범위를 넘어서 표시되기때문에 오류로 뜬다
        //int의 범위는 -21억부터 +21억까지
        long l = 1000000000000l ; //int자료로 인식을 하되, 범위를 넘어서는 자료의 경우
        //long를 쓰고 뒤에 L또는 l을 넣으면 값의 범위를 넘어서도 표시할수 있다.
        l = 1_000_000_000_000l; //앞에서 선언한 변수에 값을 업데이트 값을 나눠서 볼수 있어
        //숫자를 1000000000000으로 표시한것보다 보기 편하고 값은 똑같이 출력된다.
        System.out.println(l);
    }
}
