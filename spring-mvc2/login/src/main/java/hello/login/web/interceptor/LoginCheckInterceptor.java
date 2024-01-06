package hello.login.web.interceptor;

import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //로그인 체크의 경우 pre만 구현이 되면 된다. 로그인의 여부를 체크하는 과정이라 컨트롤러까지는 가지 않아도 되서 그런가

        String requestURI = request.getRequestURI();

        log.info("인증 체크 인터셉트 실행 {}", requestURI);

        //허용경로 구분은 인터셉트를 등록할때 따로 할수 있기때문에
        //filter에서처럼 whitelist를 따로 구분지어서 할 필요없게된다.

        HttpSession session = request.getSession();
        if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
            log.info("미인증 사용자 요청");
            //로그인으로 redirect
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }

        return true;
    }
}
