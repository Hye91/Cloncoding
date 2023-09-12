package hello.core.web;


import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; //request가 들어왔을때만 실행이 되는 상황 -> provider로 DL을 하면 된다.
    //private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody //화면 없이 텍스트로만 반환받으려고 함.
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        //MyLogger myLogger = myLoggerProvider.getObject(); //필요한 시점에 myLogger을 받을수있다,
        //HttpServletRequest 자바 규약에 의한 http request 요청 정보를 받을 수 있다.
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
