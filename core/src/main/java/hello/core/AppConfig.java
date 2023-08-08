package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImple;

public class AppConfig {

    //애플리케이션 전체를 설정하고 구성한다.
    //객체의 생성과 연결을 담당하게 된다.

    //리팩토링을 하는 이유 : 메서드명을 보는 순간 역할이 바로 드러나게 된다.
    //구현을 현재 어플리케이션에서 어떻게 하는지를 확인하기 쉽다.
    //후에 DB를 바꿔야하는 경우가 생길경우, memberRepository의 코드만 변경해주면 된다.
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository()); //new MemoryRepository를 memberRepository로 리팩토링
        //memberService역할, MemberRepository이 구분되게 표현하기 위해서 리팩토링을 진행
        //리팩토링 단축키 ctrl + alt + m
        //MemberServiceImpl의 생성과, MemoryMemberRepository의 생성을 AppConfig가 담당
        //MemoryMemberRepository객체를 생성하고, 그 참조값을 MemberServiceImpl을 생성하면서 생성자로 전달한다.
    }

    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService(){
        return new OrderServiceImple(memberRepository(), discountPolicy());
    }

    //DiscountPolicy는 직접 리팩토링
    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }

}
