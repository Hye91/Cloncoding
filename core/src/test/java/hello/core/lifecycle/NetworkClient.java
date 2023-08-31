package hello.core.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
//javax 는 스프링이 아니더라도 java측에서 지원을 해주는 Annotation이다

public class NetworkClient /*implements InitializingBean, DisposableBean*/ { //빈 생명주기 콜백을 위한 가짜 네트워크
    //인터페이스 implements를 통해서 연결 후 의존관계주입이 끝나고 연결, 연결끊는 메서드를 실행할수 있다.

    private String url; //접속해야할 서버의 url

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메시지");
    }

    //url은 외부에서 setter로 넣을수 있게하기
    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출하는 메서드
    public void connect(){
        System.out.println("connect : " + url);
    }

    //실제 연결 call시 뜨는 메시지
    public void call(String message){
        System.out.println("call : " + url + " message = " + message);
    }

    //서비스 종료시 호출하는 메서드
    public void disconnect(){
        System.out.println("close : " + url);
    }

//    public void afterPropertiesSet() throws Exception { //의존관계 주입이 끝나면 호출하는 메서드
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }

    @PostConstruct //애노테이션을 통해서 네트워크 연결 이전, 이후 호출하는 방식
    public void init(){ //의존관계 주입이 끝나면 호출하는 메서드
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

//    public void destroy() throws Exception { //연결이 끊긴 이후에 호출 되는 메서드
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    @PreDestroy
    public void close(){ //연결이 끊긴 이후에 호출 되는 메서드
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
