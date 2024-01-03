package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String addForm(@ModelAttribute @Validated LoginForm form, BindingResult bindingResult, HttpServletResponse response){
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

    //로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletResponse response){
        expireCookie(response, "memberId");
        return "redirect:/";
    }

    private static void expireCookie(HttpServletResponse response, String cookieName) {
        //로그아웃시에는 쿠키의 만료시간을 0으로 해버리면 된다
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }


}
