package chap_04;

public class _02_Else {
    public static void main(String[] args) {
        //조건문 IF Else
        //오후 2시가 안됐다면 커피를 시키고, 2시 넘었다면 디카페인 시키기
        int hour = 10;
        if (hour < 14){
            System.out.println("아이스 아메리카노 + 1");
        } else { //그 외의 경우
            System.out.println("아이스 아메리카노 (디카페인) + 1");
        }
        System.out.println("커피 주문 완료 #1");

        //오후 2시 이후이거나 모닝커피 마신경우?
        hour = 15;
        boolean morningCoffee = true ;
        if (hour >= 14 || morningCoffee){
            System.out.println("아이스 아메리카노(디카페인) + 1");
        } else { //그 외의 경우
            System.out.println("아이스 아메리카노 + 1");
        }
        System.out.println("커피 주문 완료 #2");

    }
}
