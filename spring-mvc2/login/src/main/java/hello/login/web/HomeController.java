package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.session.SessoinManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessoinManager sessoinManager;

    //@GetMapping("/")
    public String home() {

        return "home";
    }

    //@GetMapping("/") //로그인 처리 후 화면 : 쿠키를 받아와서 로그인 이후 화면을 보여주게된다
    public String homeLogin(@CookieValue(name = "memberId", required = false/*로그인 안한 사용자도 들어와야하니까*/)Long memberId,
                            Model model){ //Cookie값은 String인데 id의 값이 Long인 경우 스프링의 타입컨버팅으로 해결된다. 추후 설명

        if(memberId == null){
            return "home";
        }

        //로그인한 사용자
        Member loginMember = memberRepository.findById(memberId);

        if(loginMember == null){
            return "home";
        }

        model.addAttribute("member",loginMember);
        return "loginHome";
    }

    @GetMapping("/") //로그인 처리 후 화면 : 쿠키를 받아와서 로그인 이후 화면을 보여주게된다
    public String homeLoginV2(HttpServletRequest request, Model model){
        //세션 관리자에 저장된 회원 정보를 조회해야한다
        Member member = (Member)sessoinManager.getSession(request);
        //Object인 객체 타입을 Member로 바꾸는 캐스팅 과정이 필요하댜
        //로그인한 사용자
        if(member == null){
            return "home";
        }

        model.addAttribute("member",member);
        return "loginHome";
    }
}