package hello.core;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImple;

public class AppConfig {

    //애플리케이션 전체를 설정하고 구성한다.
    //객체의 생성과 연결을 담당하게 된다.
    public MemberService memberService(){
        return new MemberServiceImpl(new MemoryMemberRepository());
        //MemberServiceImpl의 생성과, MemoryMemberRepository의 생성을 AppConfig가 담당
        //MemoryMemberRepository객체를 생성하고, 그 참조값을 MemberServiceImpl을 생성하면서 생성자로 전달한다.
    }

    public OrderService orderService(){
        return new OrderServiceImple(new MemoryMemberRepository(), new FixDiscountPolicy());
    }

}
