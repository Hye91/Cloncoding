package chap_04;

public class _08_NestedLoop {
    public static void main(String[] args) {
        //이중 반복문
        //프로그래밍 하면 단일 반복문보다는 여러 반복문을 겹쳐서 수행해야 하는경우 있다.

        // 별 (*) 사각형 만들기
        /*

        *****
        *****
        *****
        *****
        *****

         */
        for (int i = 0; i < 5; i++) { //i= 0 1 2 3 4회에 걸쳐 진행
            System.out.print("*");
            System.out.print("*");
            System.out.print("*");
            System.out.print("*");
            System.out.print("*"); //이 다섯문장이 한번 수행되면 ***** 이라는 결과 출력
                                    // 다섯 문장을 한번에 출력하는 반복문을 만들기
            //for (int j = 0; j < 5; j++) {

            //}
            System.out.println();
        }
        System.out.println("---구분선 #1---");

        // ***** 문장을 한번에 출력하는 이중 반복문↓
        for (int i = 0; i < 5; i++) { //i= 0 1 2 3 4회에 걸쳐 진행
//            System.out.print("*");
//            System.out.print("*");
//            System.out.print("*");
//            System.out.print("*");
//            System.out.print("*"); //이 다섯문장이 한번 수행되면 ***** 이라는 결과 출력
//                                    // 다섯 문장을 한번에 출력하는 반복문을 만들기
            for (int j = 0; j < 5; j++) {
                System.out.print("*"); // ***** 이 문장이 출력
                //이 수행문은 *을 똑같이 5개씩 해야하니까 j도 0 1 2 3 4회를 진행
            }
            System.out.println();
        }

        System.out.println("---구분선 #2---");

        // 별(*) 왼쪽 삼각형 만들기
//        *
//        **
//        ***
//        ****
//        *****
        for (int i = 0; i <5 ; i++) { //줄바꿈을 5번 반복하는 작업
            System.out.print("*");
            System.out.print("*");
            System.out.print("*");
            System.out.print("*");
            System.out.print("*");
            //for (int j = 0; j < ; j++) {
            //}
            System.out.println();
        } // 별(*)로 사각형 만드는것과 똑같은 양상이 된다.

        System.out.println("---구분선 #3---");

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < i + 1 ; j++) { //for (int j = 0; j <= i  ; j++) 도 동일
                System.out.print("*");
//          for (int j = 0; j < 5; j++) {
//              System.out.print("*"); // ***** 이 문장이 출력
//              //위에 별로 네모를 만들때의 이 수행문은 *을 똑같이 5개씩 해야하니까
//              j도 0 1 2 3 4 총 5회씩 진행}
                //하지만 지금은 삼각형꼴로 진행되어야 하므로
                //처음에는 1회, 다음엔 2.. 3...4..5회씩 진행되어야한다
                // i = 0일때 j = 1이어야 인덱스 0 으로 1회 "*" 출력
                // i = 1일때 j = 2 이어야 인덱스 0, 1로 2회"**" 출력
            }
            System.out.println();
        }
        System.out.println("---구분선 #4---");

        // 별(*) 오른쪽 삼각형 만들기 ☆
//            *
//           **
//          ***
//         ****
//        *****
//        위 삼각형은 밑과 동일(가시적으로 볼때)
//        ssss*
//        sss**
//        ss***
//        s****
//        *****
//        한줄에 5개의 문자가 찍히고 그 문자들이 5줄 나오는 것
//        5줄 찍히는 그 문자열들에 조건이 2개가 된것이다. -> 동등한 조건 2개 (하위 개념X)

        //내가 시도 해본것 -> i에 해당하는 조건 밑에 j, 그리고 j밑의 조건으로 k를 건것
//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 4 - i ; j++) {
//                System.out.print("0");
//                for (int k = 0; k < i + 1; k++) {
//                    System.out.print("*");
//                }
//            }
//            System.out.println();
//        }
        //선생님이 한 것 -> i에 해당하는 조건 밑에 동등한 조건 2개를 건것.
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4 - i; j++) {
                System.out.print(" ");
            }
            for (int k = 0; k < i + 1; k++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
