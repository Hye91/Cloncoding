package chap_04;

import javax.swing.*;

public class _Quiz_04 {
    public static void main(String[] args) {
        // 조건문을 활용해 주차요금 정산 프로그램 작성
        // 주차 요금은 시간당 4000원 (일일 최대 요금은 30000원)
        //경차 또는 장애인 차량은 최종요금에서 50%할인 (장애인 차량 : 직접운전 또는 탑승 모두 포함)

        //주차 요금 예시
        //일반 차량 5시간 주차시 20000원
        //경차 5시간 주차시 10000원
        //Q . 장애인 차량 10시간 주차시 15000원 //일일 최대 주차 시간이 10시간인가?
            //그래서 일일 최대 요금 30000원의 반값인 15000원이 되는건가?
        //A . 일일 최대 요금이 30000원이란 뜻은 계산값이 30000원 이상인 경우
            //30000원으로 해주면 된다는 뜻.

        //실행 결과
        //주차 요금은 nnnnn원 입니다. << 이렇게만 출력

        // If 문으로 내가 한 것 -> 일일 최대요금 30000원에 대한 이해 부족
        // 경차와 장애인 차량은 조건이 동일한데 굳이 중복으로 작성 ( ||활용한 if 구문 작성 안함)
        // 실수를 정수형으로 바꾸는거 까먹어서 표현하지 못함.
        // 일반 차량의 경우는 굳이 조건으로 설정하지 않았어도 됐다.

        int hour = 10;
        int price = hour * 4000;
        boolean normal = false;
        boolean light = false;
        boolean special = true;

        if (normal) {
            System.out.println("일반 차량 " + hour + "시간 주차시 " + price + "원");
        } else if (light) {
            System.out.println("경차 " + hour + "시간 주차시 " + (price*0.5) + "원");
        } else if (special) {
            System.out.println("장애인 차량 " + hour + "시간 주차시 " + (price*0.5) + "원");
        } else
            System.out.println("일일 최대 요금은 " + 30000 + "원");

//        //SwitchCase 문으로 내가 한 것
//        switch (price) {
//            case 1 :
//                normal = true;
//                System.out.println("일반 차량 " + hour + "시간 주차시 " + price + "원");
//            case 2 :
//                light = true;
//                System.out.println("경차 " + hour + "시간 주차시 " + (price*0.5) + "원");
//            case 3 :
//                special = true ;
//                System.out.println("장애인 차량 " + hour + "시간 주차시 " + (price*0.5) + "원");
//                break;
//        }

        //선생님이 한 것

        int hour2 = 10; //주차시간
        boolean isSmallCar = false; //경차여부
        boolean withDisabledPerson = true; // 장애인 차량 여부

        int fee = hour2 * 4000; //시간당 4000원 곱하기 , 주차 정산 요금
        //10시간은 주차하면 40000원인데 일일 최대 요금이 30000원이니까 요금은 30000원으로 된다
        if (fee > 30000 ){
            fee = 30000;
        }

        //경차 또는 장애인 차량인 경우 50% 할인
        if (isSmallCar || withDisabledPerson ){
            fee /= 2; // fee = fee/2; 또는 fee = (int)(fee*0.5f); 또는 fee *= 0.5f;
                        // fee *= 0.5f 여기서 f 곱해주는건 실수 -> 정수화
        }
        System.out.println("주차 요금은 " + fee + "원 입니다.");
    }
    }
