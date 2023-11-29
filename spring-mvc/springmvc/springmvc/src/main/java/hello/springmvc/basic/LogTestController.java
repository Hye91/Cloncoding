package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger; //이 로거를 사용해야한다!
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass()/*현재 나의 클래스*/);
    //이거 대신해서 @Slf4j를 입력해도 가능하다
    @RequestMapping("/log-test")
    //@Controller 을 사용하게 되면 return을 할때 view를 반환하게 되는데
    //@RestController을 사용하면 (restAPI의 그 rest, return을 하면 String을 그대로 반환하게 된다.)
    //http 바디에 그 스트링 자체를 그냥 넣어버림
    public String logTest(){
        //System.out.println("log = " + log);
        //이전에는 이런 방식을 사용했다면, 이제는 이런 방식 쓰면 X
        String name = "Spring";

        log.trace("trace my log ="+ name);
        //이런 식으로 로그를 남기게 되면 java의 특성상 실제 trace가 호출되지 않더라도
        // + 의 연산은 일어나게 된다. 그래서 리소스를 남긴다! (CPU 낭비)

        log.trace(" trace log={}", name); //lv. 1
        log.debug(" debug log={}", name); //lv. 2
        log.info(" info log={}", name); //lv. 3
        log.warn(" warn log={}", name); //lv. 4
        log.error(" error log={}", name); //lv. 5
        //application.propertise에서 로깅을 보는 레벨을 정할수 있다
        //레벨이높을수록 심각도가 높다. 보통 개발 서버에서는 debug레벨로 많이 본다.
        return "ok";
    }
}
