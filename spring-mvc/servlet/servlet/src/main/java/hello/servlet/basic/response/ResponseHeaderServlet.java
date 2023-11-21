package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[staus-line]
        response.setStatus(HttpServletResponse.SC_OK); //response응답을 지정하기, 200이라고 직접 지정하는것보단 이 방법이 더 낫다

        //[response-headers]
        response.setHeader("Content-Type","text/plain;charset=utf-8"); //utf-8을 지정해야 한글을 넣어도 깨지지않는다
        response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); //캐시 완전 무효화
        response.setHeader("Pragma", "no-cache"); //HTTP 1.0버전까지 호환 캐시 무효화
        response.setHeader("my-header","hello"); //사용자 지정 헤더

        //[Header 편의 메서드]
        //content(response);
        //cookie(response);
        //redirect(response);

        //[message body]
        PrintWriter writer = response.getWriter();
        writer.println("ok, 하이");
    }

    private void content(HttpServletResponse response) {
        //Content-Type: text/plain;charset=utf-8
        //Content-Length: 2
        //response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        //response.setContentLength(2); //(생략시 자동 생성)
    }

    private void cookie(HttpServletResponse response) {
        //Set-Cookie: myCookie=good; Max-Age=600;
        //response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie); //addCookie를 지원한다.
    }

    private void redirect(HttpServletResponse response) throws IOException {
        //Status Code 302
        //Location: /basic/hello-form.html
//        response.setStatus(HttpServletResponse.SC_FOUND); //302
//        response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
