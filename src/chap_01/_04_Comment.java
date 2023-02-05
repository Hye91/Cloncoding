package chap_01;

public class _04_Comment {
    public static void main(String[] args) {
        System.out.println("(10분 전) 잠시 후 결혼식 시작 예정이오니 착석 부탁드립니다.");
        //System.out.println("(5분 전) 잠시 후 결혼식 시작 예정이오니 착석 부탁드립니다.");
        //인텔리제이에서는 반복할 문장을 선택하고 ctr + D 를 누르면 복사가 된다
        System.out.println("지금부터 결혼식을 시작하겠습니다.");
        //만약, 10분전 안내방송 이후, 식장이 정리가 되어서 5분전에 할 멘트가 굳이 필요하지
        //않은 경우, 실제 서면으로는 삭제를 하면되지만, 프로그래밍에서는 주석으로 처리할수 있다.

        int size = 120;
        size = size + 10; // 보조설명을 위해 주석을 적는 경우도 있다.
        // 어린이는 발이 빨리 자라기 때문에 사이즈 10만큼 더 큰 신발을 구매
        System.out.println("신발 사이즈" + size + "으로 보여주세요");

        //여러 줄에 대한 주석처리
        /*
        int a = 10;
        int b = 20;
        System.out.println(a + b); */

        //주석 단축키 ctrl + / : 주석 지울때도 똑같이 반복하면 지워짐.
        // 여러줄 주석 처리할때는 주석할 문장 선택하고 ctrl + shift + /
        // 주석처리가 잘 안먹을때는 한영키 눌러보고 다시 하면 잘 된다.
    }
}
