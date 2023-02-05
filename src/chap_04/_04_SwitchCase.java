package chap_04;

public class _04_SwitchCase {
    public static void main(String[] args) {
        //Switch Case

        //석차에 따른 장학금 지급
        // 1등 : 전액 장학금
        // 2등 : 반액 장학금
        // 3등 : 반액 장학금
        // 그 외 : 장학금 대상 아님


        //If Else 문 이용
        int ranking = 4; //1등 //숫자 문자열에서 양쪽이 같은지 비교할때는 ==쓴다!
        if ( ranking == 1) {
            System.out.println("전액 장학금");
        } else if (ranking == 2 || ranking == 3) {
            System.out.println("반액 장학금");
            //else if 구문 2개로 써서 2등과 3등의 수행문 나눌 수도 있다.
        } else {
            System.out.println("장학금 대상 아님");
        }
        System.out.println("조회 완료 #1");

        //Switch Case 문을 이용
            //switch ( expression ) {
            // case A : ...수행할 명령... break ;
            // case B : ...수행할 명령... break ;
            // case C : ...수행할 명령... break ;
            // ...
            // default :...수행할 명령... }
        ranking = 1;
        switch (ranking) {
            case 1 :
                System.out.println("전액 장학금");
                break; //switch case 모든 문장을 빠져나오는 역할
                        //case 1이 수행되는 조건이 맞으면 문장이 실행된다음
                        //switch case 문장을 break로 탈출.
                        //조건이 안맞으면 다음 case2로 넘어가게된다?
                        //모든 수행문을 하나하나 다 훓으면서 내려가는게 아니라
                        //case에 맞는 문장에 들어가게 되는듯
                        //그래서 그 문장이 맞으면 break로 탈출 하고
            case 2 :
                System.out.println("반액 장학금");
                break;
            case 3 :
                System.out.println("반액 장학금"); //case2와 중복이라서 밑줄이 뜬다.
                break;
            default:
                System.out.println("장학금 대상 아님");
        }
        System.out.println("조회 완료 #2");

        //case 2와 3을 통합
        ranking = 2;
        switch (ranking) {
            case 1 :
                System.out.println("전액 장학금");
                break; //switch case 모든 문장을 빠져나오는 역할
            case 2 :
            case 3 :
                System.out.println("반액 장학금");
                break; //여러 case에 대해서 동일하게 수행할때는 이렇게 표현
            default:
                System.out.println("장학금 대상 아님");
        }
        System.out.println("조회 완료 #3");

        //중고 상품의 등급에 따른 가격을 책정 (1급 : 최상, 4급 : 최하)
        int grade = 2; //등급
        int price = 7000; //기본 가격
        switch (grade) {
            case 1 :
                price += 1000; //price = price + 1000과 동일
            case 2 :
                price += 1000;
            case 3 :
                price += 1000;
                break; //따로 default 구문 설정하지 않고 끝내본 경우
        }
            // grade = 3일 경우 바로 case 3에 들어가서 문장이 실행 되고
            // price += 1000원 되서 break로 나오게 되는데
            // grade = 1일 경우 case 1에 들어가서 문장이 실행되고
            // 여기서는 각 케이스에 break가 있는게 아니라서
            // 뒤이어 case 2 와 case 3이 실행된다음 break 로 나오게 되므로
            // grade = 1인 경우 price 가 10000원이 되게 된다.
            // break를 적절히 활용해서 case의 여러 연산들을 한번에 할수있게 유도 가능하다.
        System.out.println(grade + "등급 제품의 가격 : " + price + "원");
        // 1등급 제품의 가격 : 10000원
        // 2등급 제품의 가격 : 9000원

        // If Else 와 switch case 를 쓰는 경우 나누기?
        // Switch Case 쓰는 경우 : 어떤 값이 명확한 케이스가 있는 조건
            // ex) 에러 코드에 따른 문구 출력
        // If Else 쓰는 경우 : 여러 조건 또는 어떤 범위에 해당하는 조건
//        ex) int score = 95;
//            if (score > = 95)
//                System.out.println("A");
//            else if (score >= 80) {
//                System.out.println("B");
//            }
    }
}
