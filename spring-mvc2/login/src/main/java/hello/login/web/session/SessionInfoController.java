package hello.login.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionInfoController {
    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session == null){
            return "세션이 존재하지 않습니다";
        }
        //세션 데이터 출력
        session.getAttributeNames().asIterator()
                .forEachRemaining(name -> log.info("session name ={}, value={}", name , session.getAttribute(name)));

        //Session이 기본으로 제공하는 데이터
        log.info("sessionId={}", session.getId());
        log.info("maxInactiveInterval={}", session.getMaxInactiveInterval()); //비활성화시키는 최대 텀
        log.info("creationTime={}", new Date(session.getCreationTime())); //생성시간
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime())); //마지막 세션 접근 시간
        log.info("isNew={}", session.isNew()); //새로운 세션인지 여부

        return "세션 출력";
    }
}
