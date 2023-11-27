package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {

    boolean supports(Object handler);
    //핸들러 맵핑 정보에서 요청한 핸들러를 지원할수 있는 어댑터인지 T,F로 반환하게된다
    //110v 220v 인지 파악해서 거기에 맞는 어댑터를 찾아서 꺼내오는개념

    ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException;
}
