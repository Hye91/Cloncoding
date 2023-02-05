package chap_05;

public class _04_MultiArrayLoop {
    public static void main(String[] args) {
        //다차원 배열 순회 -> for문을 겹쳐서 2번 쓰면 된다.
        String [][] seats = new String[][] {
                {"A1", "A2","A3","A4","A5"},
                {"B1", "B2","B3","B4","B5"},
                {"C1", "C2","C3","C4","C5"}
        };

        for (int i = 0; i < 3; i++) { //i는 세로 크기를 명시
            for (int j = 0; j < 5 ; j++) { //j는 가로 크기를 명시
                System.out.print(seats[i][j] + " "); //A1 A2 A3 식으로 띄어쓰기
            }
            System.out.println();
        }

        System.out.println("---구분선---");

        //줄의 갯수가 다른 경우에는 반복문 어떻게 쓰는지
        String[][] seats2 = {
                {"A1","A2","A3"}, //세로 인덱스 값 : seats2[0]
                {"B1","B2","B3","B4"}, //세로 인덱스 값 : seats2[1]
                {"C1","C2","C3","C4","C5"} //세로 인덱스 값 : seats2[2]
        };

//        .for (int i = 0; i < 3; i++) { //i는 세로 크기를 명시
//            //            for (int j = 0; j < 5 ; j++) { //j는 가로 크기를 명시
//            //                System.out.print(seats2[i][j] + " "); //A1 A2 A3 식으로 띄어쓰기
//            //            }
//            //            System.out.println();
//            //            //[0][3]에 해당하는 for문이 실행 될때 없는 자리가 되므로 오류가 뜨게 된다

        for (int i = 0; i < seats2.length; i++) { //i는 세로 크기를 명시
            for (int j = 0; j < seats2[i].length ; j++) { //j는 가로 크기를 명시
                                //seats2[i].length 세로 인덱스 값의 가로 인덱스 길이를 알수 있다.
                System.out.print(seats2[i][j] + " "); //A1 A2 A3 식으로 띄어쓰기
            }
            System.out.println();
        }

        System.out.println("---구분선2---");

        //좌석의 갯수가 엄청 많게 되면 하나하나 지정해주기가 힘들게 된다.
        //다차원 배열을 직접 반복문을 통해서 값을 초기화 하는 연습 -> 배열 '선언' 과정
        // 세로 크기 10 X 가로 크기 15에 해당하는 영화관 좌석
        String[][] seats3 = new String[10][15];
        String[] eng = {"A","B","C","D","E","F","G","H","I","J"};
        for (int i = 0; i < seats3.length; i++) { //세로 기준
            for (int j = 0; j < seats3[i].length; j++) { //가로 기준
                seats3[i][j] = eng[i] + (j + 1);
            }
        }

//        String [][] seats = new String[][] {
//                {"A1", "A2","A3","A4","A5"},
//                {"B1", "B2","B3","B4","B5"},
//                {"C1", "C2","C3","C4","C5"}
//        };
        //이렇게 하나하나 형태를 지정해서 선언하는게 아니고
        //배열의 형태를 반복문을 이용해서 선언 -> sout통해서 출력해봤자 선언이 안되었기때문에
        //null의 형태로만 출력된다.

        //표 구매 : H9, H10
        seats3[7][8] = "__";
        seats3[7][9] = "___";

        //영화관 좌석 번호 확인
        for (int i = 0; i < seats3.length; i++) { //i는 세로 크기를 명시
            for (int j = 0; j < seats3[i].length ; j++) { //j는 가로 크기를 명시
                //seats2[i].length 세로 인덱스 값의 가로 인덱스 길이를 알수 있다.
                System.out.print(seats3[i][j] + " "); //A1 A2 A3 식으로 띄어쓰기
            }
            System.out.println();
        }
    }
}
