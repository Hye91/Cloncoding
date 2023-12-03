package hello.springmvc.basic.response;


import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
//@Controller
//@ResponseBody //ResponseBody가 걸리는 어노테이션을 각각 걸지말고 클래스 단위에서 걸어줄수도있다.
////entity로 나가는것들은 바로 entity로 넘어가기때문에 Responsebody에 걸리지 않는다
@RestController //@Controller이랑 @ResponseBody를 합친것
public class ResponseBodyContoller {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        //response.getWriter().write("ok");
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    //@ResponseBody
    @GetMapping("response-body-string-v3")
    public String responseBodyV3() {
        //response.getWriter().write("ok");
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    //responseEntity에서는 응답을 지정할수 있는데 responseBody사용하면 지정못하니까 이렇게 사용한다
    //@ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData;
    }
}
