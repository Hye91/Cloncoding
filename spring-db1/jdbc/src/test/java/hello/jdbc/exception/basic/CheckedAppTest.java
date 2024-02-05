package hello.jdbc.exception.basic;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.*;

public class CheckedAppTest {

    /**
     * checkException을 사용하면 처리하지 못하는 service나 Controller에도 throws로 체크를 던져야한다
     * -> exception에 의존하게 된다. 만약, 다른  DB로 바꾸거나 (JDBC가 아닌 JPA를 사용)하는 경우
     * 모든 코드들을 바꿔야하는 경우가 생김.
     *
     * 그 문제를 해결하기 위해서 예외의 최상위 예외인 Exception을 throws한다면?
     * -> 그럼 checkException을 하는 의미가 사라진다. 확인하고 넘어가야할 예외까지 모두 던지게 되므로
     * -> 해결법은 UnCeckedException을 활용하는것
     */

    @Test
    void checked(){
        Controller controller = new Controller();
        assertThatThrownBy(()->controller.request())
                .isInstanceOf(Exception.class); //예외를 터트리는 자체를 잡는걸로 설정
    }

    static class Controller{
        Service service = new Service();

        public void request() throws SQLException, ConnectException {
            service.logic();
        }
    }

    static class Service{
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() throws ConnectException, SQLException {

            /**
             * service에서 처리하지 못하는 경우 무조건 exception을 던져야한다
             * 많은 exception이 생길수록 던져야하는 예외처리가 늘어난다 -> checkedExceptio을 요즘 사용하지 않는 이유
             */
            repository.call();
            networkClient.call();
        }
    }

    static class NetworkClient{ //서버를 호출하는 곳
        public void call() throws ConnectException {
            throw new ConnectException("연결 실패");
        }
    }

    static class Repository{
        public void call() throws SQLException {
            throw new SQLException("ex");
        }
    }
}
