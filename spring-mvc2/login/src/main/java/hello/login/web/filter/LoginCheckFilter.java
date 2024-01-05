package hello.login.web.filter;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*; //Filter는 이것을 implements받아야한다
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};
    //이 경로에 포함되는 경로들은 로그인을 하지 않아도 접근이 가능하도록 함.

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작 = {}", requestURI);
            //체크 로직
            if(isLoginCheckPath(requestURI)){
                log.info("인증 체크 로직 실행 = {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER)== null){
                    log.info("미인증 사용자 요청 ={}", requestURI);
                    //로그인화면으로 redirect
                    httpResponse.sendRedirect("/login?redirectURL =" + requestURI);
                    //로그인화면으로 돌아간다음 로그인 후, 로그인때문에 막혔던 화면으로 돌아가게하기위해서 들어온 경로를 남긴것.
                    /**
                     * "/login?redirectURL = " + requestURI 이것처럼 " "빈공간을 두면 %로 인식해서 제대로 로직이 실행되지않는다.
                     */
                    return;
                }
            }
            chain.doFilter(request,response);
        } catch (Exception e){
            throw e; //예외 로깅 가능하지만, 톰캣까지 예외를 보내주어야 함
        } finally {
            log.info("인증 체크 필터 종료 = {}", requestURI);
        }
    }
    /**
     * 화이트 리스트에 있는 경우 인증체크를 하지 않는 메서드 만들기
     */
    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whitelist,requestURI); //whitelist와 requestURI가 단순히 매칭되는지 살핀다
    }
}
