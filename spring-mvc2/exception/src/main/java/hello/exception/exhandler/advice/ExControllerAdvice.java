package hello.exception.exhandler.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice(basePackages = "hello.exception.api")
public class ExControllerAdvice { //예외처리에 대한 부분을 컨트롤러에서 따로 빼서 모은다음 적용시킨다

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandler(IllegalArgumentException e){
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD",e.getMessage());
    } //controller에서 지정한 예외가 터지면 여기서 예외를 잡게된다
    //이후 정상 흐름으로 로직이 작동되면서 200 메시지가 적용된다
    //코드까지 바꿔서 출력하고 싶다면 ResponseStatus를 붙여서 사용하면된다.

    @ExceptionHandler/*(UserException.class)생략 가능*/
    public ResponseEntity<ErrorResult> userExHandler(UserException e){
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
        //ResponseEntity 객체로 반환되는거니까 생성자 호출통해서 ErrorResult에 생성해주고 그  값을 반환해준다
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e){
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX","내부 오류");
    }

}
