package chap_06;

public class _02_Parameter {
    public static void power(int number){ // number이 parameter(매개변수,전달값)이 된다.
        //지금 정의하는 메소드는 전달값이 있는 메소드
        ///public static void + 메소드 이름(메소드에서 필요로 하는 값)
        int result = number * number;
        System.out.println(number + "의 2승은 " + result);
    }

    //전달값 2개를 받는 메소드
    public static void powerByExp(int number, int exponent) {
        //반복문 이용해 exponent만큼 반복하면서 number값을 곱해주는 메소드
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= number;
            //number = 2, exponent =3일때
            //i = 0, 1, 2 / number = 2
            // 1 * 2 = 2
            // 2 * 2 = 4
            // 4 * 2 = 8

            //number = 3, exponent =3일때
            //i = 0, 1, 2 / number = 3
            // 1 * 3 = 3
            // 3 * 3 = 9
            // 9 * 3 = 27

            //number = 10, exponent = 0일때
            //i = 0 -> i < exponent 조건 맞지 않음
            //result = 1 그대로 출력

            //number = 5, exponent = 5일때
            //i = 0, 1, 2, 3, 4 / number = 5
            // 1 * 5 = 5
            // 5 * 5 = 25
            // 25 * 5 = 125
            // 125 * 5 = 625
            // 625 * 5 = 3125

        }
        System.out.println(number + " 의" + exponent + "승은 " + result);
    }

    public static void main(String[] args) {
        //전달값, parameter
        //메소드는 값을 전달해서, 그 값을 가지고 내부적으로 수행하는 경우도 있다.

        //거듭 제곱하는 메소드 정의해보기
        // 2 -> 2 * 2 -> 4
        // 3 -> 3 * 3 -> 9
        //Argument, 인수 : 여기서 인수 = 2 가 된다.
        power(2);
        power(3);

        powerByExp(2,3); //8
        powerByExp(3,3); //27
        powerByExp(10,0); //1
        powerByExp(5,5); //3125
    }
}
