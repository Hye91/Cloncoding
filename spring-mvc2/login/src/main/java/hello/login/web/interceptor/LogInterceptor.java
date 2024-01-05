package hello.login.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j //스프링이 제공하는 인터셉터를 활용해서 로그를 남기는 인터셉터 만들기
public class LogInterceptor implements HandlerInterceptor {
    public static final String LOG_ID = "logId"; //상수 추출 단축키 : ctrl + alt + c

    //Override 단축키 : ctrl + O
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();//들어오는 요청들의 구분을 위해서 랜덤생성자 생성

        request.setAttribute(LOG_ID, uuid);
        //오류가 난 요청사항의 구분을 위해서 uuid를 사용하는거고 이걸 response에 담아주기위해서 위와 같은 로직 사용

        //@RequestMapping: HandlerMethod
        //정적 리소스: ResourceHttpRequestHandler
        //handler을 Object형으로 받기때문에 어떤 컨트롤러로 받는건지 확인하기위해서 사용하는 로직
        //다양한 핸들러를 구분하고, 특히 컨트롤러 메서드를 식별하기 위해 instanceof 연산자를 사용하여 형변환을 시도하는 로직이 필요합니다.
        // 이때, HandlerMethod은 컨트롤러 메서드를 나타내는 특별한 타입이므로, instanceof HandlerMethod을 통해 컨트롤러 메서드인지를 확인하고,
        // 맞다면 해당 핸들러를 HandlerMethod로 캐스팅하여 컨트롤러 메서드의 정보를 얻어올 수 있습니다
        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler; //호출할 컨트롤러 메서드의 모든 정보가 포함되어 있다.
        }

        log.info("REQUEST [{}][{}][{}]", uuid,requestURI,handler); //handler을 사용해서 어떤 컨트롤러가 호출되는지도 알수있다.

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("post handle [{}]", modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();

        String logId = (String) request.getAttribute(LOG_ID);
        //pre와 post의 경우에는 예외상황이 찍히지 않는다. 그래서 after에다가 예외인 상황을 넘겨줘야한다
        //log response할때 uuid를 넘겨줘야한다.

        log.info("RESPONSE [{}][{}][{}]", logId,requestURI,handler);

        if(ex != null){
            log.error("afterCompletion error!!", ex);
        }
    }
}
