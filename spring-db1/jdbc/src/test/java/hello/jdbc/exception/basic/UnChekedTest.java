package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class UnChekedTest {

    @Test
    void unChecked_catch(){
        Service service = new Service();

        service.callCatch();
    }
    @Test
    void unChecked_throws(){
        Service service = new Service();
        assertThatThrownBy(()->service.callThrows())
                .isInstanceOf(MyUnCheckedException.class);
    }

    /**
     * RuntimeException을 상속받은 예외는 언체크 예외가 된다
     */


    static class MyUnCheckedException extends RuntimeException{
        public MyUnCheckedException(String message) {
            super(message);
        }
    }

    /**
     * UnChecked 예외는
     * 예외를 잡거나 던지지 않아도 된다
     * 예외를 잡지않으면 자동으로 밖으로 던지게 된다.
     */
    static class Service{
        Repository repository = new Repository();

        /**
         * 필요한 경우 예외를 잡아서 처리하면 된다
         */
        public void callCatch(){
            try {
                repository.call();
            } catch (MyUnCheckedException e){
                //예외처리 로직
                log.info("예외처리, message={}",e.getMessage(),e);
            }
        }

        /**
         * 예외를 잡지 않아도 된다
         * 자연스럽게 상위로 넘어간다
         * Checked 예외와 다르게 Throws 선언을 하지 않아도 된다
         */
        public void callThrows(){
            repository.call();
        }
    }

    static class Repository{
        public void call(){ //throws를 생략할수있다.
            throw new MyUnCheckedException("ex");
        }
    }
}
