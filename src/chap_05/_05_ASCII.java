package chap_05;

public class _05_ASCII {
    public static void main(String[] args) {
        //아스키 코드 (ANSI) : (정보 교환을 위한) 미국 표준 코드
        //알파벳 대문자(A)는 65부터 시작, 소문자(a)는 97부터 시작, 숫자(0)는 48부터 시작

        char c = 'A'; //char 입력할때는 작은 따옴표
        System.out.println(c);
        System.out.println((int) c); //아스키 코드 표에서 A는 십진수 65

        c = 'B';
        System.out.println(c);
        System.out.println((int) c); //아스키 코드 표에서 B는 십진수 66

        c++;
        System.out.println(c);
        System.out.println((int) c);

        //char 자료형을 통해서 한개의 문자를 바로 쓸 수 있기도 하지만
        // c++처럼 연산을 통해서 정수 계산하는 것처럼 문자로부터 다음 문자를 출력할 수 있다.

        char c1 = 'a';
        System.out.println(c1);
        System.out.println((int) c1);

        c1 = 'b';
        System.out.println(c1);
        System.out.println((int) c1);

        c1++;
        System.out.println(c1);
        System.out.println((int) c1);

        char c2 = '0';
        System.out.println(c2);
        System.out.println((int) c2);

        c2 = '1';
        System.out.println(c2);
        System.out.println((int) c2);

        c2++;
        System.out.println(c2);
        System.out.println((int) c2);

//        String[][] seats3 = new String[10][15];
//        String[] eng = {"A","B","C","D","E","F","G","H","I","J"};
//        for (int i = 0; i < seats3.length; i++) { //세로 기준
//            for (int j = 0; j < seats3[i].length; j++) { //가로 기준
//                seats3[i][j] = eng[i] + (j + 1);
//            }
//        }
        //위 코드를 아스키 코드를 활용하면 편하게 작업할 수 있다.
        //일일이 String[] eng 하나하나 작성하지 않아도 된다.

        String[][] seats3 = new String[10][15];
        char ch = 'A';
        for (int i = 0; i < seats3.length; i++) {
            for (int j = 0; j < seats3[i].length; j++) {
                seats3[i][j] = String.valueOf(ch) + (j + 1);
                //String.valueOf(int) 정수형 변수 문자형으로 변환
            }
            ch++;
        }

        for (int i = 0; i < seats3.length; i++) { //i는 세로 크기를 명시
            for (int j = 0; j < seats3[i].length; j++) { //j는 가로 크기를 명시
                //seats2[i].length 세로 인덱스 값의 가로 인덱스 길이를 알수 있다.
                System.out.print(seats3[i][j] + " "); //A1 A2 A3 식으로 띄어쓰기
            }
            System.out.println();
        }

    }
}
