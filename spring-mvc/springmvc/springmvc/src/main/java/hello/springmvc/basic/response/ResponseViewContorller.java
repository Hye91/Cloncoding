package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewContorller {

    @RequestMapping("/response-view-v1")
    public ModelAndView/*뷰 템플릿을 변환해야 하므로 modelAndView로 반환*/ responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");
        //response/hello의 view템플릿에 data자리에 내가 넣은 attributeValue값을 넣겠다
        return mav;

    }

    //@ResponseBody //만약 이걸 하게되면 view로 안가고 바로 return값을 반환해서 response/hello이 출력된다
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){ //이번엔 String으로 반환해서 Model 필요

        Model mv = model.addAttribute("data", "hello!");
//        ModelAndView mav = new ModelAndView("response/hello")
//                .addObject("data", "hello!");
        //response/hello의 view템플릿에 data자리에 내가 넣은 attributeValue값을 넣겠다
        return "response/hello"; //뷰의 논리적 이름을 반환한다 (반환타입이 String일때)
        //MVC 프레임워크 만들기 v4였나?

    }

    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){ //이번엔 String으로 반환해서 Model 필요
        Model mv = model.addAttribute("data", "hello!");
    } //컨트롤러의 mapping 이름과 view의 논리적이름이 같은 경우에 Spring에서 알아서 바꿔서 인식해준다.
}
