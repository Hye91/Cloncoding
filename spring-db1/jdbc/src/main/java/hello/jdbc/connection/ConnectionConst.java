package hello.jdbc.connection;

public abstract class ConnectionConst { //상수를 모아놓고 쓰는거기때문에 인스턴스 생성이 되면 안되므로 abstract를 사용

    public static final String URL = "jdbc:h2:tcp://localhost/~/test2"; //h2 dataBase 접근 규약
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "";

}
