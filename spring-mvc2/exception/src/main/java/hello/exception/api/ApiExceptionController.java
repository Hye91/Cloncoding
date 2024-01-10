package hello.exception.api;


import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController //객체로 응답받기 위해서 Rest사용
public class ApiExceptionController {

    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id){ //memberDto를 반환하는 API
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

    @GetMapping("/api/response-status-ex1")
    public String responseStatusEx1(){
        throw new BadRequestException();
    }

    //@ResponseStatus 는 개발자가 직접 변경할 수 없는 예외에는 적용할 수 없다.
    //추가로 애노테이션을 사용하기 때문에 조건에 따라 동적으로 변경하는 것도 어렵다

    @GetMapping("/api/response-status-ex2")
    public String responseStatusEx2(){ //상태코드랑 오류메시지까지 한번에 해결할 수있는 방법
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
    }

    @GetMapping("/api/default-handler-ex")
    public String defaultException(@RequestParam(name = "data") Integer data){
                                //스프링 부트 3.2버전 이상부터는 @RequestParam(name = ) 을 필수로 적용
        return "ok";
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String memberId;
        private String name;
    }
}
