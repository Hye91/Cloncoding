package chap_02;

public class _02_Operator2 {
    public static void main(String[] args) {
        // 대입 연산자
        int num = 10; //대입
        num = num + 2;
        System.out.println(num); //12

        num = num - 2;
        System.out.println(num); //10

        num = num * 2;
        System.out.println(num); //20

        num = num / 2;
        System.out.println(num); //10

        num = num % 2;
        System.out.println(num); //0

        //복합 대입 연산자 위에서처럼 num을 중복해서 사용하지 않고 하는 방법
        num = 10;
        num +=2; // num = num + 2; 와 같은 문장
        // num = a + 2; 처럼 다른 변수를 사용해야 할때는 성립안된다.
        System.out.println(num); //12

        num -= 2;
        System.out.println(num); //10

        num *= 2;
        System.out.println(num); //20

        num /= 2;
        System.out.println(num); // 10

        num %= 2;
        System.out.println(num); //0
    }
}
