package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;


@Slf4j
@RestController
public class RequestHeaderController {
    
    //Header의 정보 가져오는 방법
    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale/*언어정보 가져오기*/,
                          @RequestHeader MultiValueMap/*하나의 키에 여러값을 받을수 있다.*/<String, String> headerMap
                                                                                        /*Header의 모든 정보를 가져오기*/,
                          @RequestHeader("host") String host /*header의 정보 하나만 가져오기*/,
                          @CookieValue(value = "myCookie", required = false/*없어도 된다는뜻*/) String cookie /*쿠키 정보 가져오기*/
                          ){
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
     }
}
