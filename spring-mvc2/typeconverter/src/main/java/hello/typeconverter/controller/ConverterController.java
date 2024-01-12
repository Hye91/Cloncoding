package hello.typeconverter.controller;

import hello.typeconverter.type.IpPort;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConverterController {

    @GetMapping("/converter-view")
    public String converterView(Model model){
        //문자를 객체로 변환하는 Converter
        model.addAttribute("number",10000);
        model.addAttribute("ipPort",new IpPort("127.0.0.1",8080));
        return "converter-view";
    }

    @GetMapping("/converter/edit") //converter가 form에는 자동으로 적용이 된다
    public String converterForm(Model model){
        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        //빈 form이 아닌 기본 ipPort를 저장해두는 form을 만들었다.
        Form form = new Form(ipPort);
        model.addAttribute("form",form);
        return "converter-form";
    }

    @PostMapping("/converter/edit") //form으로 전송받은 객체를 저장하는 메서드
    public String converterEdit(@ModelAttribute("form") Form form, Model model){
        IpPort ipPort = form.getIpPort();
        model.addAttribute("ipPort",ipPort);
        return "converter-view";
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    static class Form{

        private IpPort ipPort;

        public Form(IpPort ipPort) {
            this.ipPort = ipPort;
        }
    }
}
