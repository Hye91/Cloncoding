package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
@Slf4j
public class UnCheckedAppTest {

    /**
     * RunTimeException을 상속받는 RuntimeConnectException, RuntimeSQLException을
     * 사용해서 더이상 service와 controller에서 throws를 이용해서 예외를 던지지 않아도 된다.
     *
     */

    @Test
    void unChecked(){
        Controller controller = new Controller();
        assertThatThrownBy(()->controller.request())
                .isInstanceOf(RuntimeException.class); //예외를 터트리는 자체를 잡는걸로 설정
    }

    @Test
    void printEx(){
        Controller controller = new Controller();
        try {
            controller.request();
        } catch (Exception e){
//            e.printStackTrace(); 이 방식으로 stackTrace를 호출하는건 좋지 않다 로그로 남기는게 좋다
            log.info("ex",e);

        }
    }

    static class Controller{
        Service service = new Service();

        public void request() /*throws SQLException, ConnectException*/ {
            service.logic();
        }
    }

    static class Service{
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() /*throws ConnectException, SQLException*/ {

            repository.call();
            networkClient.call();
        }
    }

    static class NetworkClient{ //서버를 호출하는 곳
        public void call() /*throws ConnectException*/ {
            throw new RuntimeConnectException("연결 실패");
        }
    }

    static class Repository{
        public void call(){
            //SQL exception을 잡아서 대신 RunTimeException을 던지게 하는 로직
            try {
                runSQL();
            } catch (SQLException e) {
                /**
                 * 예외를 던질때는 항상 기존의 예외를 넣어줘야한다.!!
                 * 기존의 예외를 빼먹으면 무슨 SQL 때문에 RuntimeSQLException이 터진건지 알수 없게된다
                 * 기존의 e를 통해서 cause by를 통해 root cause를 확인할수 있게된다.
                 */
                throw new RuntimeSQLException(e);
            }
        }
        //SQL 예외가 터진다고 가정
        public void runSQL() throws SQLException {
            throw new SQLException("ex");
        }
    }

    static class RuntimeConnectException extends RuntimeException{
        public RuntimeConnectException(String message) {
            super(message);
        }
    }

    static class RuntimeSQLException extends RuntimeException{

        public RuntimeSQLException() {
        }

        public RuntimeSQLException(Throwable cause) {
            //cause를 사용하면 이전예외를 뭘 던졌는지 알수있다.
            super(cause);
        }
    }
}
