package chap_04;

public class _10_Break {
    public static void main(String[] args) {
        //Break
        // 매일 20마리만 파는 치킨 집 (1인당 1마리만 구매)
        //손님 50명 대기
        
        //For 문
        int max = 20;
        for (int i = 1; i <= 50 ; i++) { //i는 여기서 손님
            // 50명보다 작거나 같다는 조건을 만족하는 동안
            System.out.println( i + "번 손님, 주문하신 치킨 나왔습니다.");
            if (i == max) {
                System.out.println("금일 재료가 모두 소진되었습니다.");
            }
        }
        System.out.println("영업을 종료합니다");
        // 이렇게 작성하면 20명에게 다 팔고 나서도 영업을 종료합니다라는 문구는
        //i = 50이 되고 나서 출력되게 된다 -> break 사용해야한다

        System.out.println("---Break를 사용해야하는 이유↓---");

        int max2 = 20;
        for (int i = 1; i <= 50 ; i++) { //i는 여기서 손님
            // 50명보다 작거나 같다는 조건을 만족하는 동안
            System.out.println( i + "번 손님, 주문하신 치킨 나왔습니다.");
            if (i == max2) {
                System.out.println("금일 재료가 모두 소진되었습니다.");
                break; //반복문을 탈출
            }
        }
        System.out.println("영업을 종료합니다");

        System.out.println("-----------------------");

        //While 문
        int index = 1; //for문에서 처럼 변수 i가 없으므로 따로설정, 손님 대기번호
        while (index <= 50) {
            System.out.println( index + "번 손님, 주문하신 치킨 나왔습니다.");
            if (index == max) {
                System.out.println("금일 재료가 모두 소진되었습니다.");
                break; //반복문 탈출
            }
            index++; //while 문에서는 index의 증감을 따로 설정해줘야한다 (for문과의 차이)
            //이거 안하면 무한루프에 빠져버림 ;;
        }
        System.out.println("영업을 종료합니다.");
    }
}
