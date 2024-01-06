package hello.login.web;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import hello.login.web.argumentresolver.Login;
import hello.login.web.session.SessoinManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    //@GetMapping("/") //로그인 처리 후 화면 : 쿠키를 받아와서 로그인 이후 화면을 보여주게된다
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

    //@GetMapping("/") //로그인 처리 후 화면 : 쿠키를 받아와서 로그인 이후 화면을 보여주게된다
    public String homeLoginV3(HttpServletRequest request, Model model){

        HttpSession session = request.getSession(false);//로그인 하지 않은 사용자는 세션이 만들어지면 안되므로 false
        if(session == null){
            return "home";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        //세션에 회원데이터가 없으면 home
        if(loginMember == null){
            return "home";
        }

        //세션이 유지되면 로그인 후 화면으로 이동
        model.addAttribute("member",loginMember);
        return "loginHome";
    }

    //@GetMapping("/") //로그인 처리 후 화면 : 쿠키를 받아와서 로그인 이후 화면을 보여주게된다
    public String homeLoginV3Spring(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model){
            //스프링에서 @SessionAttribute를 활용해서 request를 사용해서 get하는 방법을 줄일수 있다
//        HttpSession session = request.getSession(false);//로그인 하지 않은 사용자는 세션이 만들어지면 안되므로 false
//        if(session == null){
//            return "home";
//        }
//
//        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        //세션에 회원데이터가 없으면 home
        if(loginMember == null){
            return "home";
        }

        //세션이 유지되면 로그인 후 화면으로 이동
        model.addAttribute("member",loginMember);
        return "loginHome";
    }

    @GetMapping("/") //로그인 처리 후 화면 : 쿠키를 받아와서 로그인 이후 화면을 보여주게된다
    public String homeLoginV3ArgumentResolver(@Login/*이 어노테이션이 있으면 자동으로 로그인된 사용자인지 알게하는것 만들기*/Member loginMember
            , Model model){ //annotation넣을때는 ctrl + space
        //Login이 제대로 동작하지않으면 modelAttribute처럼 동작을 하게된다.

        //세션에 회원데이터가 없으면 home
        if(loginMember == null){
            return "home";
        }

        //세션이 유지되면 로그인 후 화면으로 이동
        model.addAttribute("member",loginMember);
        return "loginHome";
    }
}