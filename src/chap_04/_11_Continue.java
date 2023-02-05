package chap_04;

import jdk.nashorn.internal.ir.WhileNode;

public class _11_Continue {
    public static void main(String[] args) {
        //Continue

        //만약 노쇼 손님이 있다면 -> 다음 손님에게 넘어감
        // For
        int max = 20; //최대 치킨 판매 수량
        int sold = 0; // 현재 치킨 판매 수량 , 판매 치킨수를 계산
        int noShow = 17; // 대기번호 17번 손님이 노쇼
        for (int i = 1; i <= 50 ; i++) { //손님이 50명 왔다고 계산할때
            System.out.println(i + "번 손님, 치킨 나왔습니다.");

            //치킨이 나온 후 가져가는 상황이 발생해야 하는데
            //노쇼가 있는경우 가져가지 않는 상황이 발생.
            if ( i == noShow){
                System.out.println(i + "번 손님, 노쇼로 인해 다음 손님에게 기회가 넘어갑니다.");
                continue; //이 문장이 실행되는 순간 이 밑의 문장은 실행되지 않고
                //다음 증감으로 넘어가게 된다. -> sold 카운트에 업데이트 되지 않는다.
            }
            sold++; //판매처리
            if (sold == max) { //noShow로 인해서 한명이 빠졌기때문에 사람수가 아니라
                                //판매 수로 20을 카운트해서 max와 비교해야한다.
                System.out.println("금일 재료가 모두 소진되었습니다.");
                break;
            }
        }
        System.out.println("영업을 종료합니다.");

        System.out.println("---구분선---");

        //While 문
        sold = 0 ;
        int index = 1; //손님 번호
        while ( index <= 50) {
            System.out.println(index + "번 손님, 주문하신 치킨 나왔습니다.");

            //손님이 없다면
            if (index == noShow) {
                System.out.println(index + "번 손님, 노쇼로 인해 다음 손님에게 기회가 넘어갑니다.");
                index++; //for문과 달리 While문에서는 index의 증감을 주지 않으면
                //continue문을 만나서 증감 확인 없이 index<=50 이라는 조건만 확인하고
                //반복을 할지 말지 결정을 하기 때문에 17번 손님에서 무한루프에 빠진다.
                continue;
            }
            sold++; //판매처리
            if (sold == max) {
                System.out.println("금일 재료가 모두 소진되었습니다");
                break;
            }
            index++;
        }
        System.out.println("영업을 종료합니다.");


        System.out.println("---구분선 2---");

        //index의 분산으로 헷갈릴수 있는 while문 깔끔하게 하는 방법
        sold = 0 ;
        index = 0; //손님 번호
        noShow = 17;
        while ( index < 50) {
        //while(true) { 로 index 조건을 주지 않아도 가능한데
        //이 경우에는 특정조건 만족하면 break되는 상황이기 때문에 가능한것!
        //아니라면 무한루프에 빠지게 되니 유의해야한다.
            index++;
            System.out.println(index + "번 손님, 주문하신 치킨 나왔습니다.");
            //손님이 없다면
            if (index == noShow) {
                System.out.println(index + "번 손님, 노쇼로 인해 다음 손님에게 기회가 넘어갑니다.");
                continue;
            }
            sold++; //판매처리
            if (sold == max) {
                System.out.println("금일 재료가 모두 소진되었습니다");
                break;
            }

        }
        System.out.println("영업을 종료합니다.");
    }
}
