package chap_04;

public class _09_MultipleTable {
    public static void main(String[] args) {
        //구구단
        // 2 * 1 = 2
        // 2 * 2 = 4
        // ...
        // 9 * 9 = 81

        for (int i = 2; i < 10; i++) {
            int j = 1;
            System.out.println(i + " * " + j + " = " + (i * j));
            j++;
            System.out.println(i + " * " + j + " = " + (i * j));
            j++;
            System.out.println(i + " * " + j + " = " + (i * j));
            j++;
            System.out.println(i + " * " + j + " = " + (i * j));
            j++;
            System.out.println(i + " * " + j + " = " + (i * j));
        }

        System.out.println("---구분선 #1---");

        //내가 한것! = 선생님이랑 한것! ^ㅁ^
        for (int i = 2; i < 10; i++) {
            for (int j = 1; j < 10; j++) {
                System.out.println(i + " * " + j + " = " + (i * j));
            }
            System.out.println();

        }
    }
}
