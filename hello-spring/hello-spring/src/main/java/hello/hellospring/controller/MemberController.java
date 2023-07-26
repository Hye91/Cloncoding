package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;
            //= new MemberService();
            //새로운 객체를 생성해서 하는것보다
            //스프링 컨테이너에 등록을 하고 쓰는게 좋다?

    @Autowired //이걸 사용해서 실행될때 service와 연결이 되게해준다
    public MemberController(MemberService memberService) {

        this.memberService = memberService;
    }
}
