package hello.jdbc.repository.ex;

public class MyDuplicateKeyException extends MyDbException{
    //MyDbException을 상속받아서 DB에서 발생한 예외라는걸 카테고리화해서 알수 있게했다.


    public MyDuplicateKeyException() {
    }

    public MyDuplicateKeyException(String message) {
        super(message);
    }

    public MyDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDuplicateKeyException(Throwable cause) {
        super(cause);
    }
}
