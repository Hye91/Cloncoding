package hello.core.singleton;

public class SingletonService {
    //객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다.
    //private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야 한다.

    //싱글톤 패턴의 기본 코드
    private static final SingletonService instance = new SingletonService();
    //이렇게 하면 스태틱 영역에 '클래스' 레벨로 존재하기때문에 딱 하나만 존재하게 된다.
    //SingletonService을 생성해서 instance에 자기 자신을 참조로 넣어두게 된다.

    //이런식으로 만들게 되면 자기 자신을 생성해서 참조한 다음 다른 곳에서는 생성할수 있는곳이 아무데도 없게된다.

    //조회
    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService() { //생성자
    }

    public void logic(){
        System.out.println("싱글톤 객체 호출");
    }
}
