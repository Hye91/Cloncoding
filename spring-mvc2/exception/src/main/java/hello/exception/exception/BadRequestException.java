package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.bad"/*메시지 키를 사용, messages.properties에서 꺼내오기*/)
//ExceptionHandler를 직접 구현했던걸 이 코드로 구현해주게 된다
public class BadRequestException extends RuntimeException{
}
