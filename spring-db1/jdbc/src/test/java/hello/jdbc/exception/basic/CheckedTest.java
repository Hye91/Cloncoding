package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class CheckedTest {

    @Test
    void checked_catch(){
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw(){
        Service service = new Service();
        //service에서도 잡지않고 던졌기때문에 여기서도 필수로 던지거나 잡아야한다.
        //테스트에서는 예외를밖으로 던지면 실패로 뜨기때문에 검증을 통해서 처리해줘야한다.
        assertThatThrownBy(() -> service.callThrow())
                .isInstanceOf(MyCheckedException.class);
    }

    static class MyCheckedException extends Exception{
        //컴파일러가 체크하는 Checked Exception

        /**
         * Exception을 상속받은 예외는 체크 예외가 된다
         */
        public MyCheckedException(String message) {
            super(message);
        }
    }

    /**
     * Checked 예외는
     * 예외를 잡아서 처리하거나, 던지거나 둘 중하나를 필수로 처리해야한다
     */
    static class Service{

        Repository repository = new Repository();

        /**
         * 예외를 잡아서 처리하는 코드
         */
        public void callCatch(){
            try {
                repository.call();
            } catch (MyCheckedException e) {
                //예외 처리 로직
                log.info("예외처리, message={}",e.getMessage(),e/*예외를 stackTrace로 출력할때는 이렇게 마지막에 e넣는다*/);
            }
        }

        /**
         * 예외를 잡지않고 던지는 코드
         * 체크 예외는 예외를 잡지않고 밖으로 던지려면 Throws예외를 메서드에 필수로 선언해야한다.
         */
        public void callThrow() throws MyCheckedException {
            repository.call();
        }

    }
    static class Repository{
        public void call() throws MyCheckedException {
            //체크예외인 Exception을 상속받았으므로 예외를 잡거나(catch) 던지는것(throws)중 하나를 해야한다!
            //지금의 경우에는 예외를 던져주었다. -> 이 예외를 컴파일러가 체크해주게 된다.
            throw new MyCheckedException("ex");
        }
    }
}
