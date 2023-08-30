package hello.core.lifecycle;

public class NetworkClient { //빈 생명주기 콜백을 위한 가짜 네트워크

    private String url; //접속해야할 서버의 url

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
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
}
