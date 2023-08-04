package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl(); //구현체의 공간을 만들어줘야한다.
        //회원가입 과정
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        //제대로 가입되었는지 확인
        Member findMemeber = memberService.findMember(1L);

        System.out.println("findMember = " + findMemeber.getName());
        System.out.println("new member = " + member.getName()); //soutv하면 변수들 출력값 선택할수있다.
    }
}
