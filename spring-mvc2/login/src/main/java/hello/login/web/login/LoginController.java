package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import hello.login.web.SessionConst;
import hello.login.web.session.SessoinManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final SessoinManager sessoinManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
        return "login/loginForm";
    }

    //@PostMapping("/login")
    public String login(@ModelAttribute @Validated LoginForm form, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null){
            bindingResult.reject("loginFailed", "아이디 또는 비밀번호가 맞지않습니다");
            //reject는 글로벌 오류, rejectValue는 field 오류
            return "login/loginForm";
        }

        //로그인 성공 처리
        //Cookie를 생성해서 로그인상태를 유지시켜주는것
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        //생성한 쿠키를 http response응답 보낼때 담아서 보내줘야한다.
        response.addCookie(idCookie); //쿠키에 만료시간을 넣어주지 않으면 세션쿠키가 되서 브라우저 종료시 만료된다.


        return "redirect:/"; //로그인이 성공하면 홈으로 돌려보내는것
    }

    //@PostMapping("/login") // 직접만든 sessionManager사용하기
    public String loginV2(@ModelAttribute @Validated LoginForm form, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null){
            bindingResult.reject("loginFailed", "아이디 또는 비밀번호가 맞지않습니다");
            //reject는 글로벌 오류, rejectValue는 field 오류
            return "login/loginForm";
        }

        //로그인 성공 처리
        //세션관리자를 통해서 세션을 생성하고, 회원 데이터 보관
//        //Cookie를 생성해서 로그인상태를 유지시켜주는것
//        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
//        //생성한 쿠키를 http response응답 보낼때 담아서 보내줘야한다.
//        response.addCookie(idCookie); //쿠키에 만료시간을 넣어주지 않으면 세션쿠키가 되서 브라우저 종료시 만료된다.
        sessoinManager.createSession(loginMember,response);
        return "redirect:/"; //로그인이 성공하면 홈으로 돌려보내는것
    }

    //@PostMapping("/login") //직접만든 세션아닌 HttpSession사용하기
    public String loginV3(@ModelAttribute @Validated LoginForm form, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null){
            bindingResult.reject("loginFailed", "아이디 또는 비밀번호가 맞지않습니다");
            //reject는 글로벌 오류, rejectValue는 field 오류
            return "login/loginForm";
        }

        //로그인 성공 처리
        //세션이 있으면 세션을 반환, 없으면 신규 세션을 생성해서 반환
        HttpSession session = request.getSession(true);
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:/"; //로그인이 성공하면 홈으로 돌려보내는것
    }

    @PostMapping("/login") //직접만든 세션아닌 HttpSession사용하기
    public String loginV4(@ModelAttribute @Validated LoginForm form, BindingResult bindingResult,
                          @RequestParam(defaultValue = "/") String redirectURL,/*로그인 막힌 후에 로그인하고 다시 그 화면으로 돌아가기위함*/
                          HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null){
            bindingResult.reject("loginFailed", "아이디 또는 비밀번호가 맞지않습니다");
            return "login/loginForm";
        }

        //로그인 성공 처리
        //세션이 있으면 세션을 반환, 없으면 신규 세션을 생성해서 반환
        HttpSession session = request.getSession(true);
        //세션에 로그인 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        //로그인 성공 후에 다시 막혔던 부분으로 돌아가는것 구현.
        return "redirect:" + redirectURL; //로그인이 성공하면 홈으로 돌려보내는것

    }


    //로그아웃
    //@PostMapping("/logout")
    public String logout(HttpServletResponse response){
        expireCookie(response, "memberId");
        return "redirect:/";
    }

    //@PostMapping("/logout")
    public String logoutV2(HttpServletRequest request){
//        expireCookie(response, "memberId");
        sessoinManager.expire(request);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request){
//        expireCookie(response, "memberId");
//        sessoinManager.expire(request);
        HttpSession session = request.getSession(false);//false로 설정하면 세션이 없을경우 null로 반환한다
        if(session != null){
            session.invalidate(); //세션과 그 안의 값들이 다 날아가게 된다
        }

        return "redirect:/";
    }

    private static void expireCookie(HttpServletResponse response, String cookieName) {
        //로그아웃시에는 쿠키의 만료시간을 0으로 해버리면 된다
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }


}
