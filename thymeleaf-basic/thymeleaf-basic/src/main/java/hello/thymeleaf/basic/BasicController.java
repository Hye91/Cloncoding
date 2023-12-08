package hello.thymeleaf.basic;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("/text-basic")
    public String textBasic(Model model){
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-basic";
    }

    @GetMapping("/text-unescaped")
    public String textUnescaped(Model model){
        model.addAttribute("data", "Hello <b>Spring!</b>");
        return "basic/text-unescaped";
    }

    @GetMapping("/variable")
    public String variable(Model model){
        //Object 방법
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);
        //List 방법
        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);
        //Map 방법
        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users",list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(Model model, HttpServletRequest request,
                               HttpServletResponse response, HttpSession session){
        model.addAttribute("request", request);
        model.addAttribute("response", response);
        model.addAttribute("servletContext", request.getServletContext());
        session.setAttribute("sessionData","Hello Session");
        //타임리프에서 session에 데이터를 담아서 타임리프로 꺼낼수있다.
        return "basic/basic-objects";
    }

    @Component("helloBean") //빈의 이름을 helloBean이라고 지정
    static class HelloBean{
        public String hello(String data){
            return "Hello" + data;
        }
    }

    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("/link")
    public String link(Model model){
        model.addAttribute("param1","data1");
        model.addAttribute("param2","data2");
        return "basic/link";
    }

    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data","Spring!");
        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model){
        model.addAttribute("nullData",null); //null값이 들어가면 연산 어떻게 되는지를 본다.
        model.addAttribute("data","Spring");
        return "basic/operation";
    }

    @GetMapping("/attribute")
    public String attribute(Model model){
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model){
        return "basic/each";
    }

    @Data //내부에서 쓰는 클래스 만들어주기
    static class User{
        private String username;
        private int age;

        public User(String username, int age) {
            this.username = username;
            this.age = age;
        }
    }
}
