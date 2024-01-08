package hello.exception.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
public class ErrorPageController { //오류는 발생시키는 것이 아닌 '화면'을 위한 컨트롤러

    /**
     * 여러개의 행을 한번에선택하려고 할때는 Column Selection Mode
     * 단어단위로 선택을 하고 싶다면 ctrl + shift + (->)화살표
     */

    //RequestDispatcher 상수로 정의되어 있음 (ctrl + N 하고 RequestDispatcher검색해서 들어가면 상수 다 볼수있다)
    //exception이 터져서 was까지 간 다음 이 정보들이 setAttribute해서 담기게 되어 우리가 정보를 확인할수 있게된다

    //javax -> jakarta로 변경후 모든 오류 로그에 담기는거 확인
    public static final String ERROR_EXCEPTION = "jakarta.servlet.error.exception";
    public static final String ERROR_EXCEPTION_TYPE = "jakarta.servlet.error.exception_type";
    public static final String ERROR_MESSAGE = "jakarta.servlet.error.message";
    public static final String ERROR_REQUEST_URI = "jakarta.servlet.error.request_uri";
    public static final String ERROR_SERVLET_NAME = "jakarta.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE = "jakarta.servlet.error.status_code";

    //WAS <- 필터 <- 서블릿 <- 인터셉터 <- 컨트롤러(예외발생)
    //WAS -> 필터 -> 서블릿 -> 인터셉터 -> 컨트롤러 호출
    //이 두단계를 거쳐서 오류 컨트롤러가 호출되기 때문에 어떤 오류인지 담기 위해서 request가 필요하다

    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response){
        log.info("errorPage 404");
        printErrorInfo(request);
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletRequest request, HttpServletResponse response){
        log.info("errorPage 500");
        printErrorInfo(request);
        return "error-page/500";
    }

    @RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE /*Accept 타입이 json의 경우 호출*/)
    public ResponseEntity<Map<String, Object>> /*반환을 json으로 해야하므로 responseEntity사용*/ errorPage500Api(
            HttpServletRequest request, HttpServletResponse response
    ){
        log.info("API ErrorPage 500");
        Map<String, Object> result = new HashMap<>();

        Exception ex = (Exception) request.getAttribute(ERROR_EXCEPTION); //Exception으로 캐스팅
        result.put("status", request.getAttribute(ERROR_STATUS_CODE));
        result.put("message", ex.getMessage());
        Integer statusCode = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        return new ResponseEntity<>()
    }

    //에러정보 출력
    private void printErrorInfo(HttpServletRequest request){
        log.info("ERROR_EXCEPTION: {}}", request.getAttribute(ERROR_EXCEPTION));
        log.info("ERROR_EXCEPTION_TYPE: {}", request.getAttribute(ERROR_EXCEPTION_TYPE));
        log.info("ERROR_MESSAGE: {}", request.getAttribute(ERROR_MESSAGE));
        log.info("ERROR_REQUEST_URI: {}", request.getAttribute(ERROR_REQUEST_URI));
        log.info("ERROR_SERVLET_NAME: {}", request.getAttribute(ERROR_SERVLET_NAME));
        log.info("ERROR_STATUS_CODE: {}", request.getAttribute(ERROR_STATUS_CODE));
        log.info("dispatcherType={}",request.getDispatcherType());
        //클라이언트로 부터 발생한 정상 요청인지, 아니면 오류 페이지를 출력하기 위한 내부 요청인지 구분하기 위해서
        //dispatcherType을 사용하게 된다
    }
}
