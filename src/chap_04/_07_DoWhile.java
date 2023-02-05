package chap_04;

public class _07_DoWhile {
    public static void main(String[] args) {
        // 반복문 DoWhile
        int distance = 25; //전체 거리 25m
        int move = 0; // 현재 이동 거리 0m
        int height = 2; //사람의 키를 2m로 가정하면 손을 뻗은 키 만큼은 빼고 이동하면된다
        while (move + height < distance) { //한번에 이동할때 move + height 만큼 이동하게 된다.
            System.out.println("발차기를 계속 합니다");
            System.out.println("현재 이동 거리 : " + move);
            move += 3; // 한번 이동할때마다 3m씩 이동
        }
        System.out.println("도착했습니다.");

        System.out.println("---반복문 #1---");

        //키가 엄청나게 큰 사람이 수영
        move = 0;
        height = 25; //키가 25m
        while (move + height < distance) { //한번에 이동할때 move + height 만큼 이동하게 된다.
            System.out.println("발차기를 계속 합니다");
            System.out.println("현재 이동 거리 : " + move);
            move += 3; // 한번 이동할때마다 3m씩 이동
        } //while 반복문에서 조건이 참일때 계속 진행하게 되는데
            //키가 25m가 되버리면 조건이 처음부터 맞지 않기때문에 전혀 진행이 되지 않는다.
        System.out.println("도착했습니다.");

        System.out.println("---반복문 #2---");

        //Do While 반복문 : 조건 상관없이 일단 do를 진행하고 나서 조건을 확인한 다음에
        // 조건에 따라서 계속 반복할지를 결정하게 된다. -> 최소 한번은 수행된다.
        // do {
        // } while ( 조건 ) ;
        do {
            System.out.println("발차기를 계속 합니다");
            System.out.println("현재 이동 거리 : " + move);
            move += 3;
        } while (move + height < distance);
        System.out.println("도착했습니다.");
    }
}
