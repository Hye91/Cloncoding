package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class SpringMemberSaveControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {

        //이전에는 HttpServlet의 request를 이용해서 각각의 정보를 가져왔다면
        //여기서는 FrontController에서 받은 정보를 Map에 정보를 다 넣어서 거기서 꺼내오는 방식
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model에 데이터를 보관해야 한다.
        //request.setAttribute("member",member); HttpServlet을 model로 사용할때는 SetAttribute에 저장을 했는데
        //지금 버전에서는 다른 방식으로 modelView를 활용해서 member 저장한다.
        ModelAndView mv = new ModelAndView("save-result");
        //mv.getModel().put("member",member);
        mv.addObject("member",member);
        return mv;

    }

}
