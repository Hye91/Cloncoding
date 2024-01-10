package hello.exception.api;


import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {

    //Controller 내부에서 일어난 예외의 경우에만 ExceptionHandler이 처리해주는데
    //각각의 IllegalArgumentException,UserException은 그들의 자식 클래스까지 포함해서 처리해준다
    //이때 Exception(예외들의 최상위 클래스)도 마찬가지로 그 밑의 자식 클래스까지 포함해서 처리하지만
    //앞서 정의한 IllegalArgumentException,UserException이 처리하지 못한 예외를 처리해주게 된다.

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

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id){
        if(id.equals("ex")){
            throw new RuntimeException("잘못된 사용자");
        }
        if(id.equals("bad")){
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if(id.equals("user-ex")){
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id,"hello" + id);
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String memberId;
        private String name;
    }
}
