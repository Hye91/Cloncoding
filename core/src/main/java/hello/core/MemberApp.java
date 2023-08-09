package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        //기존에는 직접 객체를 생성하고 했지만 AppConfig를 이용해서도 만들어보기
        //AppConfig 사용하는버전
//        AppConfig appConfig =new AppConfig();
//        MemberService memberService = appConfig.memberService(); //MemberServiceImpl을 주게된다.

        //스프랑 사용하는 버전
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //AppConfig에 있는 환경설정 정보를 가지고 스프링 컨테이너에 객체 생성을 한걸 집어 넣어주게된다.
        //applicationContext이 스프링 컨테이너가 된다. ApplicationContext는 인터페이스
        //AnnotationConfigApplicationContext : Annotation 기반으로 자바 스프링 Context를 만들어라
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        //MemberService memberService = new MemberServiceImpl(); //구현체의 공간을 만들어줘야한다.
        //회원가입 과정
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        //제대로 가입되었는지 확인
        Member findMemeber = memberService.findMember(1L);

        System.out.println("findMember = " + findMemeber.getName());
        System.out.println("new member = " + member.getName()); //soutv하면 변수들 출력값 선택할수있다.
    }
}
