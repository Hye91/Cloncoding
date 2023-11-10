package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    //헤더정보어떻게 출력하는지 보기위함.
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStratLine(request); //ctrl + alt + m : 메서드뽑아내기
        printHeaders(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    private static void printStratLine(HttpServletRequest request) { //start Line 정보
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); //GET
        System.out.println("request.getProtocol() = " + request.getProtocol()); //HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http
        System.out.println("request.getRequestURL() = " + request.getRequestURL());// http://localhost:8080/request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI());// /request-header
        System.out.println("request.getQueryString() = " + request.getQueryString());//username=hi
        System.out.println("request.isSecure() = " + request.isSecure()); //https사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }


    private void printHeaders(HttpServletRequest request) {//Header 모든 정보
        System.out.println("--- Headers - start ---");

//        Enumeration<String> headerNames = request.getHeaderNames(); //ctrl + alt + v : 상위 값 꺼내기
//        //getHeaderNames하면 헤더의 정보 다 꺼내올수있다.
//        while(headerNames.hasMoreElements()){ //다음 요소가 있는지 물어보기
//            String headerName = headerNames.nextElement(); //다음요소가 있으면 값을 꺼내기
//            System.out.println(headerName + ": " + headerName); //값 출력
//        }
        //요즘 사용 문법
        request.getHeaderNames().asIterator()
                        .forEachRemaining(headerName -> System.out.println(headerName + ": " + headerName));

        //헤더정보 하나만 조회하려고 할때
        //request.getHeader("host");

        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    //Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header convinient start ---");
        System.out.println("[Host convinient]");
        System.out.println("request.getServerName() = " +
                request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " +
                request.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language convinient]"); //언어세팅 정보
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " +
                        locale));
        System.out.println("request.getLocale() = " + request.getLocale()); //우선순위 제일 높은게 뽑힌다
        System.out.println();
        System.out.println("[cookie convinient]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content convinient]");
        System.out.println("request.getContentType() = " +
                request.getContentType()); //get에서는 body에 담긴 정보가 없기때문에 null로 뜬다.
        System.out.println("request.getContentLength() = " +
                request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " +
                request.getCharacterEncoding());
        System.out.println("--- Header convinient end ---");
        System.out.println();
    }

    private void printEtc(HttpServletRequest request) { //기타정보
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " +
                request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " +
                request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " +
                request.getRemotePort()); //
        System.out.println();
        System.out.println("[Local 정보]"); //나의 서버에 대한 정보
        System.out.println("request.getLocalName() = " +
                request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " +
                request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " +
                request.getLocalPort()); //
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }

}
