package hello.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {
    //오류처리를 다시 WAS까지 흘러간 다음 해결하는게 아닌 HandlerExceptionResolver에서 처리를 끝내는 방식으로 해보는것.

    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        try {
            if(ex instanceof UserException){
                log.info("UserException to 400");
                String acceptHeader = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);//오류코드 400설정

                if("application/json".equals(acceptHeader)){
                    Map<String,Object> errorResult = new HashMap<>();
                    errorResult.put("ex",ex.getClass());
                    errorResult.put("message", ex.getMessage());
                    String result = objectMapper.writeValueAsString(errorResult);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(result);
                    return new ModelAndView(); //여기서는 아무것도 넘기지 않는다
                    //예외는 먹어버리고 정상적으로 return이 진행된다.
                    //이렇게 진행하면 다시 servlet에 가서 오류를 확인하고 컨트롤러를 호출하는 과정은 일어나지않고 끝나게 된다.
                } else {
                    // html/text와 같은 다른 타입이 넘어올때
                    return new ModelAndView("error/500");
                    //json 타입이 아닌경우에는 BasicController에서 하는것처럼 view를 반환한다
                }

            }

        } catch (IOException e){
            log.error("resolver ex", e);
        }

        return null;
    }
}
