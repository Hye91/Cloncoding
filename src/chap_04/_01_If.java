package chap_04;

public class _01_If {
    public static void main(String[] args) {
        // 조건문 If
        //커피를 오후 2시 이전에 마시면 괜찮지만 오후 2시 이후에 마시면 밤에 잠을 잘 못잔다.
        //그래서 오후 2시 이전에는 커피를 시키고, 2시 이후에는 안시킨다.
        int hour =10; // 오전 10시
        //if ( 조건 )
            //수행할 명령
        if (hour < 14)
            System.out.println("Ice Americano +1 ");
            System.out.println("샷추가");
        System.out.println("커피 주문 완료 #1"); //조건이 참일경우 위의 문장실행

        int hour2 = 15;
        if (hour2 < 14)
            System.out.println("Ice Americano +1");
            System.out.println("샷추가"); //거짓일때도 샷추가가 실행이 됨.
                //if 조건이 참일때 수행하는 문장이 한개면 위 문장처럼 적으면 되지만(생략 가능)
                //(중괄호로 감싸주는 습관들이는게 좋긴하다)
                //두개 이상인 경우 수행문장을 중괄호로 감싸줘야 한다.
        System.out.println("커피 주문 완료#2");
        //중괄호로 수행할 문장 감싸안은 if 조건문 ↓
        int hour3 = 15;
        if (hour3 < 14) {
            System.out.println("Ice Americano +1");
            System.out.println("샷추가");
            }
        System.out.println("커피 주문 완료#3");

        //오후 2시 이전이라고 하더라도 아침에 커피를 마셨다면 총 2잔을 마시게 되서
        //밤에 잠을 잘 못잔다는 가정하에
        //오후 2시 이전임에도 모닝커피를 마셨다면 커피를 주문하지 않는 조건문 (조건2개 비교)
        //오후 2시 이전, 모닝 커피를 마시지 않은 경우?
        hour = 10; //hour 변수의 업데이트
        boolean morningCoffee = false;
        //if (hour <= 14 && morningCoffee == false){
        // morningCoffee(=false) == false -> true 라는결과
        // !morningCoffee -> true 라는 결과 둘이 동일하다.
        if (hour <= 14 && !morningCoffee){ // !붙이면 논리 부정연산자
            System.out.println("Ice Americano +1");
        }
        System.out.println("커피 주문 완료#4");

        //오후 2시 이후거나 모닝커피를 마신 경우 디카페인 주문
        hour = 15;
        morningCoffee = true;
        //if ( hour >= 14 || morningCoffee == true) {
        if ( hour >= 14 || morningCoffee) {
            System.out.println("Decaf Ice Americano + 1");
        }
        System.out.println("커피 주문 완료#5");
    }
}
