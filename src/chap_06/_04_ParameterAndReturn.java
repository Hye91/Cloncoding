package chap_06;

public class _04_ParameterAndReturn {
//    public static void power(int number){
//        int result = number * number;
//        System.out.println(number + "의 2승은 " + result);
//    }
//    //전달값 2개를 받는 메소드
//    public static void powerByExp(int number, int exponent) {
//        //반복문 이용해 exponent만큼 반복하면서 number값을 곱해주는 메소드
//        int result = 1;
//        for (int i = 0; i < exponent; i++) {
//            result *= number;
//        }
//        System.out.println(number + " 의" + exponent + "승은 " + result);
//    }
    //매소드 안에서 출력하지 않고 (매소드 내에서는 연산까지만 하고)
    // 연산된 결과를 반환해주고 메인 영역에서 값을 출력해주기
    public static int getPower(int number){
        int result = number * number;
        return result; //return number * number;도 가능
    }

    public static int getPowerByExp (int number, int exponent) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= number;
        }
        return result;
    }

    public static void main(String[] args) {
        //전달값과 반환값이 있는 메소드
        int retVal = getPower(2); //반환값을 저장하기 위한 변수 정의
        System.out.println(retVal);

        retVal = getPower(3);
        System.out.println(retVal);

        int retPower = getPowerByExp(2,3);
        System.out.println(retPower);

        System.out.println(getPowerByExp(2,4));
    }
}
