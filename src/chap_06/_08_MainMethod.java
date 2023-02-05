package chap_06;

public class _08_MainMethod {
    public static void main(String[] args) { //main 이용해서 만든게 main 메소드였다!
        for (String s : args) {
            System.out.println(s);
        }

        // 1. 도서 조회
        // 2. 도서 대출
        // 3. 도서 반납
        if(args.length == 1){
            switch (args[0]){
                case "1" :
                    System.out.println("도서 조회 메뉴 입니다.");
                    break;
                case "2" :
                    System.out.println("도서 대출 메뉴 입니다.");
                    break;
                case "3" :
                    System.out.println("도서 반납 메뉴 입니다.");
                    break;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        } else {
            System.out.println("사용법 1~3 메뉴 중 하나를 입력하세요.");
        }
    }
}
