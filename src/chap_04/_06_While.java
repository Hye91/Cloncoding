package chap_04;

public class _06_While {
    public static void main(String[] args) {
        //반복문 While
        //For 문은 정해져 있는 횟수만큼 반복
        //횟수가 정확히 없으면 할수 없다.
        // ex. 수영시 몇번의 발차기를 해야 25m를 도달? -> 사람마다 다르다.
        // 횟수가 정해져있지 않고 어떤 조건이 참인 동안 반복해서 문장 수행할때 While 사용
        int distance = 25; //수영 전체 거리 25m
        int move = 0; //현재 이동 거리 0m
        //while (조건) {}
        while ( move < distance ) { //현재 이동거리가 전체거리보다 작다는 조건이 참인동안 반복
            System.out.println("발차기를 계속 합니다");
            System.out.println("현재 이동 거리 : " + move);
            move += 3; //한번 움직일때 3m씩 이동한다고 가정
        }
        System.out.println("도착하였습니다.");

        // 무한 루프
        move = 0;
        while ( move < distance ) { //현재 이동거리가 전체거리보다 작다는 조건이 참인동안 반복
            System.out.println("발차기를 계속 합니다");
            System.out.println("현재 이동 거리 : " + move);
            //move += 3; //한번 움직일때 3m씩 이동한다고 가정
            // move+= 3으로 탈출 조건을 걸어주는게 중요하다. 아니면 무한 루프에 빠지게 된다.
        }
        System.out.println("도착하였습니다.");
    }
}
