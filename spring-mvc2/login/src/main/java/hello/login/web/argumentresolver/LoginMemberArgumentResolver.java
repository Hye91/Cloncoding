package hello.login.web.argumentresolver;

import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {

    //filter또는 interceptor에 @Component를 사용(Bean으로 등록)해서 Resolver에서 의존성 주입하면
    //의존관계 주입을 받을수 있게 된다.
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter 실행");

        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        //컨트롤러 호출 전에 해당 @Login 어노테이션이 존재하는지를 확인
        boolean hasMemberType = Member.class.isAssignableFrom(parameter.getParameterType());
        //어노테이션이 적용되는 클래스의 타입이 Member이 맞는지를 확인

        return hasLoginAnnotation && hasMemberType;
    }

    @Override //위의 supportsParameter이 true여야 실행되고 false면 실행되지 않는다
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        //실제 argument를 반환해줘야한다.
        log.info("resolveArgument 실행");

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false); //세션을 꺼내온다
        if(session == null){
            return null;
        }

        return session.getAttribute(SessionConst.LOGIN_MEMBER);
        //member의 값을 넣어주게되는것
    }
}
