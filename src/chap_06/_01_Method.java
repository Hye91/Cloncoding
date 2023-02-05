package chap_06;

public class _01_Method {
    //메소드 정의
    public static void sayHello() {
        //public static void + 메소드 이름(주로 동사를 사용)
        System.out.println("안녕하세요 메소드입니다.");
    } //메소드를 '정의'한 것
    //메소드는 한번 정의되고 나면 밑에서 원하는만큼 호출해서 사용할수 있다.

    public static void main(String[] args) {
        //☆Method : 기능을 하는 코드들의 묶음 -> 파이썬의 함수와 비슷하다
        //어떤 기능이 정의된 상자라고 생각하면 된다.
        // X -> [□ + 3] -> Y : X=3인 경우 Y = 6 '[□ + 3]'이 부분이 메소드

        // 메소드 '호출' : 메소드를 사용하는걸 호출이라고 한다.
        System.out.println("메소드 호출 전");
        sayHello();
        System.out.println("메소드 호출 후");
        //보통 프로그램을 진행 시킬 때는 위쪽에서 아래쪽으로 흘러가며 실행되는데
        //메소드를 실행할때는 위에 정의된 메소드 안으로 진입을 하게 되는것.
        //그래서 그 메소드 안에서 진행이 된 다음 다시 돌아와서
        //다음줄의 문장이 실행되게 된다.
    }
}
