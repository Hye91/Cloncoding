package hello.jdbc.repository.ex;

/**
 * RunTimeException으로 처리한 예외들을 모아서 한번에 처리하는 곳인가?
 */
public class MyDbException extends RuntimeException{ //런타임 예외를 상속받아서 언체크예외가 된다

    public MyDbException() {
    }

    public MyDbException(String message) {
        super(message);
    }

    public MyDbException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDbException(Throwable cause) {
        super(cause);
    }
}
