package chap_02;

public class _01_Operator1 {
    public static void main(String[] args) {
        // 산술 연산자

        //일반 연산
        System.out.println(4 + 2); //6
        System.out.println(4 - 2); //2
        System.out.println(4 * 2); //8
        System.out.println(4 / 2); //2
        System.out.println(5 / 2); //2.5 -> 정수 간의 연산일때는 결과도 정수로
        // 나와서 2.5아닌 2가 값으로 나오게 된다.
        System.out.println(2 / 4); //0.5 -> 0
        System.out.println(4 % 2); //4를 2로 나눴을때의 나머지 %
        System.out.println(5 % 2);

        // 우선 순위 연산
        System.out.println(2 + 2 * 2); //6 곱하기 연산이 우선
        System.out.println((2 + 2) * 2); //더하기를 먼저 하고 곱하기 하고싶을때
        System.out.println(2 + (2 * 2));

        // 변수 이용한 연산
        int a = 20;
        int b = 10;
        int c;

        c = a + b;
        System.out.println(c); //30

        c = a - b;
        System.out.println(c); //10

        c = a * b;
        System.out.println(c); //200

        c = a / b;
        System.out.println(c); // 2

        c = a % b;
        System.out.println(c); //0

        // 증감 연산 ++, --
        int val;
        val = 10;
        System.out.println(val); // 10
        //val++; //문장이 수행 된 다음에 val연산 수행
        //++val; //val의 연산을 먼저 하고 나서 다음 문장이 수행
        System.out.println(++val); //11
        System.out.println(val); //11

        val = 10;
        System.out.println(val); //10
        System.out.println(val++); //10? ++이 뒤에 있는경우
        //val값으로 일단 문장을 수행
        System.out.println(val); //11 앞의 문장에서 10출력후 1을 더해주는
        //연산이 진행되었기때문에 val의 값은 11이 된다

        val = 10;
        System.out.println(val);
        System.out.println(--val); //9
        System.out.println(val); //9

        val = 10;
        System.out.println(val);
        System.out.println(val--); //10
        System.out.println(val); //9

        // 은행 대기번호 표
        int waiting = 0; //처음 대기 번호 뽑았을때, 첫손님
        System.out.println("대기 인원 : " + waiting++); //대기 인원 0
        System.out.println("대기 인원 : " + waiting++); //대기 인원 1
        System.out.println("대기 인원 : " + waiting++); //대기 인원 2
        System.out.println("총 대기인원 : " + waiting); //대기 인원 3
    }
}
