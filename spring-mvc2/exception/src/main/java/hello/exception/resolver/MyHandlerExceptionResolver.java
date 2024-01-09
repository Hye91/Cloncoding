package hello.exception.resolver;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        log.info("call resolver", ex);

        try {
            if (ex instanceof IllegalArgumentException) {
                log.info("IllegalArgumentException resolver to 400");
                //IllegalArgumentException이 오면 error를 400으로 넘겨서 클라이언트의 실수임을 나타낼것

                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());
                return new ModelAndView();
                //빈값으로 넘기게되면 WAS까지 정상적인 흐름으로 넘기게된다. 예외를 먹어버린것
                //그래서 servlet Container에서 sendError을 통해서 400으로 왔다는것만 인식하게 된다.
            }
        } catch (IOException e) {
           log.info("resolver ex", e);
        }

        return null;
        //면, 다음 ExceptionResolver 를 찾아서 실행한다.
        // 만약 처리할 수 있는 ExceptionResolver 가 없으면 예외 처리가 안되고,
        // 기존에 발생한 예외를 서블릿 밖으로 던진다.
    }
}
