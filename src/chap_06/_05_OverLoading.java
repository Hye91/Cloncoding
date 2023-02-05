package chap_06;

public class _05_OverLoading {
    public static int getPower(int number) {
        int result = number * number;
        return result; //return number * number;도 가능
    }
//    public static int getPowerStr(String strNumber){
//        //문자열로 된 값을 받을 수도 있다 "4"처럼 이렇게 받은 문자열을
//        //다시 정수형태로 바꿔서 연산을 해서 결과를 반환해주는 메소드 필요할때 사용
//        int number = Integer.parseInt(strNumber); //문자열 값에서 정수 뽑아서 반환
//        return number * number;
//    }
    //지금은 문자형 문자열인데 다른 double이나 boolean의 형태가 될수도 있다
    //이때마다 다른 이름의 메소드를 사용하게 되면 호출할때 혼란이 올 수 있다.
    //이럴때 메소드 오버로딩을 사용한다. (이름을 똑같이 해주는것)
    //전달값의 타입이 다르거나, 자료형이 다르거나 또는 전달값의 갯수가 다르면
    //똑같은 이름의 메소드를 얼마든지 중복해서 만들수 있다.

    public static int getPower(String strNumber) { //전달값의 타입이 다른 경우
        int number = Integer.parseInt(strNumber);
        return number * number;
    }

    //        public static int getPowerByExp ( int number, int exponent){
//            int result = 1;
//            for (int i = 0; i < exponent; i++) {
//                result *= number;
//            }
//            return result;
//        }

    // ctrl + shift + L :엉망인 코드 정렬 정리

    public static int getPower(int number, int exponent) { // 전달값의 갯수가 다른 경우
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= number;
        }
        return result;
    }

    public static void main(String[] args) {
        //메소드 오버로딩
        //이름이 같은 메소드를 여러개 만드는것. -> 반환값이 다른 형태로는 중복 정의 불가.
        // 1. 전달 값의 타입이 다르거나
        // 2. 전달 값의 갯수가 다르거나

        System.out.println(getPower(3));
        System.out.println(getPower("3"));

        System.out.println(getPower(3, 3));
    }
}
