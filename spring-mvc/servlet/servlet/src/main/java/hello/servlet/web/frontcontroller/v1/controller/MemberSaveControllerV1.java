package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberSaveControllerV1 implements ControllerV1 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //요청정보 파싱
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        //비즈니스 로직 호출
        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model에 데이터를 보관해야 한다.
        request.setAttribute("member",member); //request 내부에 저장을 할수 있는 map같은게 있어서 model로 사용가능

        //view에 데이터 던져준다
        String viewPath = "/WEB-INF/views/save-result.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }
}
