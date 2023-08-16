package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {
    //싱글톤 : 하나의 JVM 안에는 하나의 객체 인스턴스만 생성되도록 하는 패턴
    @Test
    @DisplayName("스프링 없는 순수한 DI컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();

        // 1. 조회 : 호출할때마다 객체를 생성하는지 조회
        MemberService memberService1 = appConfig.memberService();
        MemberService memberService2 = appConfig.memberService();

        //2. 참조값이 다른것을 확인.
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
//        memberService1 = hello.core.member.MemberServiceImpl@2a5c8d3f
//        memberService2 = hello.core.member.MemberServiceImpl@752325ad
        // 호출할때마다 다른 객체를 생성하게 된다. -> JVM 메모리에 계속 객체가 생성되서 올라가게된다.

        //memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

//    public static void main(String[] args) {
//        SingletonService singletonService = new SingletonService();
//    } static 영역에 private으로 SingletonService가 만들어져있으므로 다른 데서 생성을 할수 없게된다.

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);
//        singletonService1 = hello.core.singleton.SingletonService@7a8c8dcf
//        singletonService2 = hello.core.singleton.SingletonService@7a8c8dcf 같은 객체 인스턴스 확인됨.

        assertThat(singletonService1).isSameAs(singletonService2);
        // Same : 참조가 같은지 비교 / Equal : java의 equal과 같이 값이 같은지 비교

        //스프링 컨테이너를 사용하면 스프링 컨테이너가 기본적으로 객체를 싱글톤 패턴으로 다 관리해준다.
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(AppConfig.class);

        //빈 이름으로 조회하기
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
