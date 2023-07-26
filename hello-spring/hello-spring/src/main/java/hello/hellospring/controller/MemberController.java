package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {
    //@Repository 나 @Service는 직접 등록을 해주는 방법이 있지만
    //@Controller은 직접해줄수 없다. 스프링에서 관리를 해주는것이므로

    private final MemberService memberService;
            //= new MemberService();
            //새로운 객체를 생성해서 하는것보다
            //스프링 컨테이너에 등록을 하고 쓰는게 좋다?

    @Autowired //이걸 사용해서 실행될때 service와 연결이 되게해준다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){

        return "members/createMemberForm";
    }
    @PostMapping("/members/new")
    public String create(MemberForm form){
        //나는 이거 MemberForm Dto로 배우지 않았나..?
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        //입력받은 값을 Service의 join 메서드를 통해서
        //레포에 저장을 하는 과정을 거친다.
        //서비스에서 비즈니스 로직 구현.

        return "redirect:/"; //회원가입하고나서 홈화면으로 돌림
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        // "members"이라는 이름으로 members를 Model에 추가.
        //memberList의 members에 값을 넘겨주게 된다.
        return "members/memberList";
    }
}
