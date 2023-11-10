package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 * */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //전체 파라미터 조회
        System.out.println("[all parameter 조회] - start");

        request.getParameterNames().asIterator()
                        .forEachRemaining(paraName -> System.out.println(paraName/*키*/
                                + "=" + request.getParameter(paraName)/*값*/));

        System.out.println("[all parameter 조회] - end");

        System.out.println();

        System.out.println("[single parameter 조회]");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("age = " + age);
        System.out.println("username = " + username);

        System.out.println();

        System.out.println("[same name, double parameter]");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name); //username이름이 중복인 경우 배열로 돌리게 되는듯함.
        }

        response.getWriter().write("ok");
    }
}
