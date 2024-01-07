package hello.exception.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ErrorPageController { //오류는 발생시키는 것이 아닌 '화면'을 위한 컨트롤러

    //WAS <- 필터 <- 서블릿 <- 인터셉터 <- 컨트롤러(예외발생)
    //WAS -> 필터 -> 서블릿 -> 인터셉터 -> 컨트롤러 호출
    //이 두단계를 거쳐서 오류 컨트롤러가 호출되기 때문에 어떤 오류인지 담기 위해서 request가 필요하다

    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response){
        log.info("errorPage 404");
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletRequest request, HttpServletResponse response){
        log.info("errorPage 500");
        return "error-page/500";
    }
}
