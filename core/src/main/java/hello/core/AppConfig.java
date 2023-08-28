package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImple;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //설정정보를 담당하는 어노테이션
public class AppConfig {

    //제어의 역전을 일으키는 AppConfig를 IoC 컨테이너/ DI컨테이너 라고한다.
    //spring이 Di 컨테이너 역할을 하게된다.

    //애플리케이션 전체를 설정하고 구성한다.
    //객체의 생성과 연결을 담당하게 된다.

    //리팩토링을 하는 이유 : 메서드명을 보는 순간 역할이 바로 드러나게 된다.
    //구현을 현재 어플리케이션에서 어떻게 하는지를 확인하기 쉽다.
    //후에 DB를 바꿔야하는 경우가 생길경우, memberRepository의 코드만 변경해주면 된다.
    //스프링 빈은 @Bean 이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다.

    // @Bean memberService() -> new MemoryMemberRepository()
    // @Bean orderService() -> new MemoryMemberRepository() --> 이렇게 되면 Singelton이 깨지는게 아닌가??
    // 검증해보자! memberServiceImpl, orderServiceImple에서 getMemberRepository만들어서 테스트

    @Bean //빈 어노테이션으로 스프링 컨테이너에 등록이 되게 된다.
    public MemberService memberService(){
        //실제로 호출은 몇번되는지 확인하기 위한 코드 작성
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); //new MemoryRepository를 memberRepository로 리팩토링
        //memberService역할, MemberRepository이 구분되게 표현하기 위해서 리팩토링을 진행
        //리팩토링 단축키 ctrl + alt + m
        //MemberServiceImpl의 생성과, MemoryMemberRepository의 생성을 AppConfig가 담당
        //MemoryMemberRepository객체를 생성하고, 그 참조값을 MemberServiceImpl을 생성하면서 생성자로 전달한다.
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImple(memberRepository(), discountPolicy());
        //return null;
    }

    //DiscountPolicy는 직접 리팩토링
    @Bean
    public DiscountPolicy discountPolicy(){
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
