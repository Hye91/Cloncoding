package chap_05;

public class _03_MultiArray {
    public static void main(String[] args) {
        // 다차원 배열 (2차원 배열)

        // 소규모 영화관 좌석
        // A1 - A5
        // B1 - B5
        // C1 - C5

        String[] seatA = {"A1", "A2","A3","A4","A5"};
        String[] seatB = {"B1", "B2","B3","B4","B5"};
        String[] seatC = {"C1", "C2","C3","C4","C5"};

        //Alt 누르고 스크롤 하면 원하는 영역만큼 지정할 수 있다.

        // 이렇게 하는 것도 가능하나, 좌석이 A ~ Z까지 있다고 하면 굉장히 많아지게 된다.
        // 같은 값이지만 서로 다른 변수에다가 따로 저장을 해서 활용하기엔 힘들다.
        // 하나의 배열로 합치면 그 배열이름만 알고 있으면 언제 어디서든지 접근할 수 있다.

        //2차원 배열 선언 방법 []의 갯수 만큼 차원을 결정하게 된다.
        // 3 X 5 크기의 2차원 배열
        String [][] seats = new String[][] {
                {"A1", "A2","A3","A4","A5"},
                {"B1", "B2","B3","B4","B5"},
                {"C1", "C2","C3","C4","C5"}
        };

        //seats[A, B, C에 해당하는 인덱스][그 행에 2에 해당하는 인덱스]
        //B2에 접근 하려면?
        System.out.println(seats[1][1]);

        //C5에 접근하려면?
        System.out.println(seats[2][4]);

        //다차원 배열에서도 모든 줄에 똑같은 갯수의 데이터가 있어야 하는건 아니다.
        //첫줄에는 3칸, 둘째 줄에는 4칸, 셋째 줄에는 5칸
        String[][] seats2 = {
                {"A1","A2","A3"},
                {"B1","B2","B3","B4"},
                {"C1","C2","C3","C4","C5"}
        };
        // A3에 접근하려면?
        System.out.println(seats2[0][2]);

        //A5에 접근해 본다면? -> 배열의 index가 배열의 범위를 벗어난다고 오류가 뜬다.
        // System.out.println(seats2[0][4]);

        // C5에 접근하려면?
        System.out.println(seats2[2][4]);

        //3차원 배열 만들기 (세로 X 가로 X 높이)
//        String[][][] marray = new String[][][] {
//                {{}, {}, {}},
//                {{}, {}, {}},
//                {{}, {}, {}}
//        };
//        String[][][] marray2 = new String[3][3][3];
    }
}
