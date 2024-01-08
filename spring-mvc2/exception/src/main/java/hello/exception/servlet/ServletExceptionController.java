package hello.exception.servlet;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Slf4j
@Controller
public class ServletExceptionController {

    //Servlet exception 전체 흐름.
    // WAS <- 필터 <- 서블릿 <- 인터셉터 <- ServletExceptionController(예외발생)
    // WAS(WebServerCustomizer에서 오류페이지 생성) -> 필터 -> 서블릿 -> 인터셉터 -> ErrorPageController 호출

    @GetMapping("/error-ex")
    public void errorEx(){
        throw new RuntimeException("예외 발생!"); //예외를 그냥 던져주는것 이때는 500상태의 에러가 뜬다

    }

    @GetMapping("/error-404") //HttpServletResponse 가 제공하는 sendError 라는 메서드를 사용
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404,"404 오류!");
        //sendError을 통해서 내가 원하는 오류 상태코드를 직접 던져주는것 가능
    }

    @GetMapping("/error-500") //HttpServletResponse 가 제공하는 sendError 라는 메서드를 사용
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500,"500 오류!");
    }
}
