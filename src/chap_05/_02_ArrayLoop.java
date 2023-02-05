package chap_05;

public class _02_ArrayLoop {
    public static void main(String[] args) {
        //배열의 순회
        String[] coffees = {"아메리카노", "카페모카", "라떼", "카푸치노"}; //1차원 배열
//        System.out.println(coffees[0] + " 하나");
//        System.out.println(coffees[1] + " 하나");
//        System.out.println(coffees[2] + " 하나");
//        System.out.println(coffees[3] + " 하나");
//        //배열을 씀에도 이렇게 sout을 통해서 하나하나 구현해야하면
//        //배열을 쓰는 이점이 없는 듯 하다. -> 배열 요소가 100개면 100개 다 쳐야한다.
//        //coffees[]안의 숫자는 순차적으로 증가하므로 반복문을 이용해서
//        //배열의 요소를 순회 해 볼수 있다.

        //for 반복문 순회
        for (int i = 0; i < 4 ; i++) {
            System.out.println(coffees[i] + " 하나");
        }
        System.out.println("주세요.");

        System.out.println("---구분선 1---");

        //만약 배열의 갯수, 크기를 모른다면? ->배열의 길이를 알수 있는 방법이 있다.
        //배열의 길이를 이용한 순회
        for (int i = 0; i < coffees.length; i++) {
            System.out.println(coffees[i] + " 하나");
        }
        System.out.println("주세요.");

        System.out.println("---구분선 2---");

        // enhanced for (for -each) 반복문 : 배열 또는 리스트 순회 할때 쓰기 좋다
        //for (배열과 똑같은 자료형의 변수 + 이름 : 순회할 배열이름) {}
        for (String coffee : coffees) {
            System.out.println(coffee + " 하나");
        }
        // coffees 배열에 있는 값들을 순서대로 순회하는데 그때 그때 순회되는 값을
        // coffee라는 이름으로 받아서 사용하겠다.
        System.out.println("주세요");

        // index의 값을 이용해서 처리를 해야하는 경우 -> for i 사용
        // 배열의 모든 요소를 순회하면서 작업 해야하는 경우 -> for each 사용

    }
}
