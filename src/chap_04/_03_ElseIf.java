package chap_04;

public class _03_ElseIf {
    public static void main(String[] args) {
        //조건문 ElseIf
        //한라봉 에이드가 있으면 +1
        //없다면(또는) 망고 주스 있으면 +1
        //둘다 없다면(또는) 아메리카노 +1

        boolean hallabongAde = true ;
        boolean mangoJuice = true ;

        if (hallabongAde) {
            System.out.println("한라봉 에이드 + 1");
        } else if (mangoJuice) { //첫번째 조건인 한라봉 에이드가 없으면 다음 조건 망고주스
            System.out.println("망고 주스 + 1");
        } else { //둘 다 없는 그 외의 경우
            System.out.println("아이스 아메리카노 + 1");
        }
        System.out.println("커피 주문 완료 #1");

        // else if 는 여러번 사용가능
        hallabongAde = false ;
        mangoJuice = false ;
        boolean orangeJuice = true ;
        
        if (hallabongAde) {
            System.out.println("한라봉 에이드 + 1");
        } else if (mangoJuice) {
            System.out.println("망고 주스 + 1");
        } else if (orangeJuice) {
            System.out.println("오렌지 주스 + 1");
        } else {
            System.out.println("아이스 아메리카노 + 1");
        }
        System.out.println("커피 주문 완료 #2");

        //else는 없어도 가능
        hallabongAde = false ;
        mangoJuice = false ;
        orangeJuice = false ;

        if (hallabongAde) {
            System.out.println("한라봉 에이드 + 1");
        } else if (mangoJuice) {
            System.out.println("망고 주스 + 1");
        } else if (orangeJuice) {
            System.out.println("오렌지 주스 + 1");
        }
        System.out.println("커피 주문 완료 #3");
    }
}
