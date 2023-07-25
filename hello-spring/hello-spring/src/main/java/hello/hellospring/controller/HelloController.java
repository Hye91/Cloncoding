package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello") //정적 컨텐츠 방식
    public String hello(Model model){
        model.addAttribute("data","hello!");
        return "hello";
    }

    @GetMapping("hello-mvc") //MVC방법
    public String helloMvc(@RequestParam("name") String name, Model model){
        //외부에서 url 파라미터를 받아서 사용해보기
        //model에 담으면 렌더링할때 사용하게 된다.
        model.addAttribute("name",name);
        //파라미터로 넘어온 name을 넘긴다?
        return "hello-template";
    }

    @GetMapping("hello-string") //API방법
    @ResponseBody //http 통신부의 body부분에 내가 직접 넣어주겠다는 뜻.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; //html로 변환하지 않고 데이터 그대로 가져간다!
    }

    //문자가 아닌 데이터를 넣어야 할때의 API방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); //새로운 객체 만들기
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
