package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyJsonController {

    private ObjectMapper objectMapper = new ObjectMapper();
    //JSON 결과를 파싱해서 사용할 수 있는 자바 객체로 변환하려면 Jackson, Gson 같은 JSON 변환
    //라이브러리를 추가해서 사용해야 한다. 스프링 부트로 Spring MVC를 선택하면 기본으로 Jackson
    //라이브러리( ObjectMapper )를 함께 제공한다

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //inpuStream읽어야한다
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("info messageBody={}", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);//readValue(String content, Class<T> valueType)
        //messageBody에 들어있는 JSON 문자열을 HelloData 클래스의 객체로 변환
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody) throws IOException {
        //inpuStream읽어야한다
//        ServletInputStream inputStream = request.getInputStream();
//        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("info messageBody={}", messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);//readValue(String content, Class<T> valueType)
        //messageBody에 들어있는 JSON 문자열을 HelloData 클래스의 객체로 변환
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData/*String아닌 바로 객체를 넣을 수 있다*/) {

        //log.info("info messageBody={}", messageBody);
        //HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        //이 과정도 굳이 필요하지 않다고 생각해서 생략해보기
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJsonV4(HttpEntity<HelloData> helloData) {

        //log.info("info messageBody={}", messageBody);
        //HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        //이 과정도 굳이 필요하지 않다고 생각해서 생략해보기
        HelloData body = helloData.getBody();
        log.info("username={}, age={}", body.getUsername(),body.getAge() );

        return "ok";
    }

    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJsonV5(@RequestBody HelloData data) {

        log.info("username={}, age={}", data.getUsername(),data.getAge() );

        return data; //HTTP 메시지 컨버터가 response로 나갈때 객체로 나가게된다.
        //json으로 들어온 값 그대로 컨버터가 나갈때도 적용시켜서 json형태로 내보낸다. (json -> 객체 -> json 형태)
    }
}
