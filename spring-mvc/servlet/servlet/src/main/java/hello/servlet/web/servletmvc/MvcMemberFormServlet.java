package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet { //컨트롤러 역할
    @Override //MVC 패턴을 적용할때, 지금의 service는 view인 JSP로 이동시켜주면된다
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp"; //이 곳의 경로로 이동하게 된다
        // /WEB-INF에 있는 자원들은 외부에서 호출해도 호출되지 않게된다.
        // 컨트롤러를 거쳐야 호출되게 하는 WAS법칙이다
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//컨트롤러에서 뷰로 이동할때 사용
        dispatcher.forward(request,response); //서블릿(컨트롤러)에서 jsp(view)를 호출할수 있다, redirect가 아니다
        //서버 내부에서 메서드 호출하듯이 호출되는것.그래서 url-Pattern도 변경되지 않는다
    }
}
