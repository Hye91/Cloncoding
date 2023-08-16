package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImple;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    // @Bean memberService() -> new MemoryMemberRepository()
    // @Bean orderService() -> new MemoryMemberRepository() --> 이렇게 되면 Singelton이 깨지는게 아닌가??
    // 검증해보자! memberServiceImpl, orderServiceImple에서 getMemberRepository만들어서 테스트


    //@Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고, 스프링 빈이 없으면 생성
    //해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다

    @Test
    void configurationTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImple orderService = ac.getBean("orderService", OrderServiceImple.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);//진짜 인터페이스를 통한 memberRepository 꺼내기

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
        // 두개가 같으므로 싱글톤이 깨지지 않는다!
    }

    @Test
    void configurationDeep(){
        //왜 memberRepository가 1번만 호출되면서 singleton이 유지되는지 테스트
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        AppConfig bean = ac.getBean(AppConfig.class);
        //파라미터로 넘긴 값은 스프링 빈으로 등록된다. 그래서 AppConfig 도 스프링 빈이 된다.

        System.out.println("bean = " + bean.getClass()); //등록된 빈의 class 타입이 뭔지 알수 있다.
        //순수한 클래스라면 class hello.core.AppConfig 출력되어야 한다.
        //그런데 예상과는 다르게 클래스 명에 xxxCGLIB가 붙으면서 상당히 복잡해진 것을 볼 수 있다.

    }
}
