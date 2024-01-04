package hello.login.web.session;

import hello.login.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SessoinManagerTest {

    SessoinManager sessoinManager = new SessoinManager();

    @Test
    void sessionTest(){

        //세션 생성
        MockHttpServletResponse response = new MockHttpServletResponse();
        //HttpServletResponse는 인터페이스다. 사용하기 위해서는 구현체가 필요하게 되는데
        //클래스에서 사용할때는 구현체를 tomcat이 별도로 제공해준다.
        //그래서 이렇게 테스트용을 위한 MockHttpServletResponse을 스프링이 제공해주게된다.
        Member member = new Member();
        sessoinManager.createSession(member,response);

        //요청에 응답 쿠키가 저장
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(response.getCookies());

        //세션조회
        Object result = sessoinManager.getSession(request);
        assertThat(result).isEqualTo(member);

        //세션만료
        sessoinManager.expire(request);

        Object expired = sessoinManager.getSession(request);
        assertThat(expired).isNull();

    }
}