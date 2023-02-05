package chap_05;

public class _Quiz_05 {
    public static void main(String[] args) {
        //신발 사이즈는 250부터 295까지 5단위로 증가
        // 신발 사이즈 수는 총 10가지

        //실행 결과
        // 사이즈 250 (재고 있음)
        // 사이즈 255 (재고 있음)
        // 사이즈 260 (재고 있음)
        // 사이즈 265 (재고 있음)
        // 사이즈 270 (재고 있음)
        // 사이즈 275 (재고 있음)
        // 사이즈 280 (재고 있음)
        // 사이즈 285 (재고 있음)
        // 사이즈 290 (재고 있음)
        // 사이즈 295 (재고 있음)

        //내가 한 것 -> 배열 선언한게 아님...
        for (int i = 250; i <300 ; i += 5) {
            System.out.println("사이즈 " + i + " (재고 있음)");
        }

        System.out.println("---배열 선언으로 해보기---");

//        int[] sizeArray = new int[9];
//        for (int j = 250; j < 300 ; j +=5 ) {
//            System.out.println("사이즈 " + sizeArray[j] + " (재고 있음)");
//        }
        //위 처럼 작성하면 오류가 뜬다. -> 내가 작성한건 250~295까지 증가하는 '내용'에 대한 반복문
        //'배열'을 '선언'해야 하므로 배열 크기에 대한 정의를 해야함!!!!!

        //배열의 '선언' ->의자 만들어주기.
        int[] sizeArray = new int[10]; //신발 사이즈 수는 10 = 배열의 크기 10
        for (int i = 0; i < sizeArray.length; i++) {
            sizeArray[i] = 250 + (5 * i); //배열 내의 규칙 만들어주기
        }

        //배열의 출력
        for (int size : sizeArray) {
            System.out.println("사이즈 " + size + " (재고 있음)");
        }

        System.out.println("---내가 다시 혼자 해보기---");

        //의자의 크기와 규칙을 설정해주는것 : 배열의 선언
        int[] braSize = new int[7];
        for (int i = 0; i < braSize.length; i++) {
            braSize[i] = 65 + (5 * i);
        }

        //내가 만든 의자를 보여주는것. foreach 활용
        for (int bra: braSize) {
            System.out.println("브라 사이즈 : " + bra);
        }
    }
}
