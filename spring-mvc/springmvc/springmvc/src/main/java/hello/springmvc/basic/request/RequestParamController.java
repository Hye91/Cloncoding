package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {

    //요청 파라미터 : 쿼리 파라미터, form형식

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        //HttpServletRequest가 제공하는 getParameter을 사용하는 것.
        log.info("info username={}, age={}",username,age);

        response.getWriter().write("ok");
        //void타입이면서 gerWriter을 사용하면 값을 쓴게 나오게 된다.
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName/*변수명은 원하는걸 아무거나 써도 된다*/,
            @RequestParam("age") int memberAge
    ){
        log.info("info username={}, age={}",memberName,memberAge);
        return "ok";
        //String타입으로 반환하고 return에 String값을 넣게 되면 controller은 논리view이름을 찾게 된다!
        //mvc 프레임워크 만들기 v4버전!
        //이때 내가 원하는 String값을 그대로 http응답에 넣고 싶다면 @ReaponseBody를 사용하면 된다
        //아니면 class단위에 @RestController을 사용해도된다.
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username/*변수명은 원하는걸 아무거나 써도 된다*/,
            @RequestParam int age
    ){
        log.info("info username={}, age={}",username,age);
        return "ok";
    }
    //스프링 부트 3.2버전의 경우 parameter생략하는 옵션을 사용할수 없다
    //부트 버전을 낮추든지, 아니면기본 실행설정을 gradle로 해야함. -> 부트버전 하향시킴

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username,int age)
    {
        log.info("info username={}, age={}",username,age);
        return "ok";
    }
    //이것도 마찬가지로 스프링부트 3.2버전의 경우 parameter생략 옵션 사용불가로 뜬다
    //String , int , Integer 등의 단순 타입이면 @RequestParam 도 생략 가능

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true) String username,
            //required속성을 통해서 꼭 필요한 값 여부를 선택해서 입력할수 있다.
            //파라미터 이름만 있고 값이 없는 경우 빈문자로 통과 (null =/= ""(빈공백))
            @RequestParam(required = false) Integer age
            //이렇게 호출하게 되면 500에러가 뜬다
            //java에서 기본형에는 null값이 들어갈수 없기때문에!
            //만약 false로 설정을 할 경우에는 int아닌 객체형 Integer(객체형을 null형 가능)을 넣어야한다.
    )
    {
        log.info("info username={}, age={}",username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age
            //값이 들어오지 않을 경우를 예상해서 기본값을 설정해줄수 있다.
    )
    {
        log.info("info username={}, age={}",username,age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Objects> paramMap)
            //모든 요청 parameter을 다 받고 싶은 경우 map형식을 사용해서 다 받아올수 있게된다.
    {
        log.info("info username={}, age={}",paramMap.get("username"),paramMap.get("age"));
        return "ok";
    }
}
