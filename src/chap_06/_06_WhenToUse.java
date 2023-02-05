package chap_06;

public class _06_WhenToUse {

    public static int getPower(int number) {
        // int result = number * number;
        // return result; //return number * number;도 가능
        // getPower 내에서 밑의 전달값 2개 받는 getPower을 호출해서
        // 위의 동작을 정의할 수 있다.
        return getPower(number,2);
        //전달 값 1개를 받는 getPower 메소드를
        //전달 값 2개 받는 getPower을 이용해서 정의 해 줄 수 있게 된다.
        // 이걸 통해서 2승을 표현하고 싶으면 number값만 입력하면 되고
        // 3승 이상을 표현하고 싶으면 인자를 2개를 넣어주면 된다.
    }
    public static int getPower (int number, int exponent){
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result *= number;
        }
        return result;
    }
    public static void main(String[] args) {
        //메소드 필요 이유
        //똑같은 동작을 어떤 수에 대해서 값만 조금 바꿔서 반복하는 경우
        //메소드를 통해서 여러번 반복되는 코드의 중복을 줄일 수 있다.
        // 코드의 수정할때도 더 간편해질 수 있다. -> 코드 유지보수에 유리

//        //2의 2승을 구하기
//        int result = 1;
//        for (int i = 0; i < 2; i++) {
//            result *= 2;
//        }
//        System.out.println(result); // 2 * 2 = 4

        //메소드 이용해서 2의 2승 구하기
        System.out.println(getPower(2,2));

//        //3의 3승을 구하기
//        result = 1;
//        for (int i = 0; i < 3; i++) {
//            result *= 3;
//        }
//        System.out.println(result); // 3 * 3 * 3 = 27

        //메소드 이용해서 3의 3승 구하기
        System.out.println(getPower(3,3));

//        //4의 2승 구하기
//        result = 1;
//        for (int i = 0; i < 2; i++) {
//            result *= 4;
//        }
//        System.out.println(result); // 4 * 4 = 16

        //메소드 이용해서 4의 2승 구하기
        System.out.println(getPower(4,2));

    }
}
