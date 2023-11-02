package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServelet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.service(req, resp); //ctrl + O 단축키 //service 메서드가 호출된다

        System.out.println("HelloServelet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        String username = request.getParameter("username"); //ctrl + arl + v : 위에 단계 꺼내기였나
        //request를 queryParameter로 요청을 보내는걸 가능하게 한다
        System.out.println("username = " + username);

        response.setContentType("text/plain"); //1. 응답을 단순 텍스트 타입으로 받게된다
        response.setCharacterEncoding("utf-8"); //2. 인코딩 정보를 알려주게 된다 -> 1과 2는 헤더 정보에 들어가게 된다
        
        response.getWriter().write("hello " + username);//http 메시지 바디에 텍스트가 들어가게된다
    }

}
