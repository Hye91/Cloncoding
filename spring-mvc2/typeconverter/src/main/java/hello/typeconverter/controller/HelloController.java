package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request){
        String data = request.getParameter("data"); //문자 타입 조회
        Integer intValue = Integer.valueOf(data); //숫자 타입으로 변경
        System.out.println("intValue = " + intValue);
        return "ok";
    }

    @GetMapping("/hello-v2") //requestParam을 통해서는 String타입의 10이 보내지는데 converter을 통해서 int타입의 10이 등록되게 된다
    public String helloV2(@RequestParam(name = "data") Integer data){
        System.out.println("data = " + data);
        return "ok";
    }

    @GetMapping("/ip-port")
    public String ipPort(@RequestParam(name = "ipPort") IpPort ipPort){
        System.out.println("ipPort IP =" + ipPort.getIp());
        System.out.println("ipPort PORT =" + ipPort.getPort());
        return "ok";
    }
}
