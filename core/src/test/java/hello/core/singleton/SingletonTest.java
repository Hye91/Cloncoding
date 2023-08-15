package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
}
