package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();
    @Override
    public ModelView process(Map<String, String> paramMap) {

        //이전에는 HttpServlet의 request를 이용해서 각각의 정보를 가져왔다면
        //여기서는 FrontController에서 받은 정보를 Map에 정보를 다 넣어서 거기서 꺼내오는 방식
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

        Member member = new Member(username, age);
        memberRepository.save(member);

        //Model에 데이터를 보관해야 한다.
        //request.setAttribute("member",member); HttpServlet을 model로 사용할때는 SetAttribute에 저장을 했는데
        //지금 버전에서는 다른 방식으로 modelView를 활용해서 member 저장한다.
        ModelView mv = new ModelView("save-result");
        mv.getModel().put("member",member);
        return mv;

    }
}
