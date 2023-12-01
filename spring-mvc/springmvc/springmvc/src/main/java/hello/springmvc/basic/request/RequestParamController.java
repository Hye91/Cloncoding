package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Slf4j
@Controller
public class RequestParamController {

    //요청 파라미터 : 쿼리 파라미터, form형식

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        //HttpServletRequest가 제공하는 getParameter을 사용하는 것.
        log.info("info username={}, age={}",username,age);

        response.getWriter().write("ok");
        //void타입이면서 gerWriter을 사용하면 값을 쓴게 나오게 된다.
    }
}
