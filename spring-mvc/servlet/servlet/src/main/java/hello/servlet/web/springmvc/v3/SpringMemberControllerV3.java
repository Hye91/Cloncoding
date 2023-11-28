package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    //RequestMapping은 메서드 단위로 되기때문에 원하는 식으로
    //비슷한 작동을 하는 Controller 묶어서 실행할수 있다.
    //@RequestMapping(value = "/new-form", method = RequestMethod.GET)
    //get, post를 구별하지 않고 호춯하는 것보다는 목적에 맞게 지정해주는 것이 더 좋다.
    @GetMapping("/new-form") //목적에 맞는 방식을 지정해서 쓰는 맵핑
    public String newForm(){
        return "new-form";
    } //Spring 애노테이션 기반 컨트롤러는 유연하게 설계되어 있어서 뮨자로 반환해도 된다.

    //@RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
            //애노테이션 기반 컨트롤러는 파라미터를 직접 받을수 있다.
    ) {
//        String username = request.getParameter("username");
//        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model에 데이터를 보관해야 한다.
        //request.setAttribute("member",member); HttpServlet을 model로 사용할때는 SetAttribute에 저장을 했는데
        //지금 버전에서는 다른 방식으로 modelView를 활용해서 member 저장한다.
//        ModelAndView mv = new ModelAndView();
//        //mv.getModel().put("member",member);
//        mv.addObject("member",member);
        model.addAttribute("member", member);
        return "save-result";

    }

    //@RequestMapping(method = RequestMethod.GET) //경로를 더할게 없는 경우에는 그냥 이렇게만 해도 된다
    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();

//        ModelAndView mv = new ModelAndView("members");
//        //mv.getModel().put("members",members);
//        mv.addObject("members",members);
        model.addAttribute("members", members);
        return "members";
    }
}
