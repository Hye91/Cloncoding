package hello.core.benaFind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByname(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass() = " + memberService.getClass()); //어떤 구현체를 사용하는지 조회?
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //memberService가 MemberServiceImpl의 인스턴스면  true
    }
    @Test
    @DisplayName("이름없이 타입으로 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByname2(){
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class); //구현체로 조회
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //memberService가 MemberServiceImpl의 인스턴스면  true
        //public MemberService memberService(){
        //        return new MemberServiceImpl(memberRepository()); return에 해당하는
        // instance 타입(구현체)으로도 조회가 가능하다
    }

    //실패테스트
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findByNameX(){
//        ac.getBean("dddd",MemberService.class);
        // MemberService memberService = ac.getBean("dddd", MemberService.class);
        //검색이 안되는걸 검증
        //무조건 NoSuchBeanDefinitionException이 예외가 터져야 테스트가 성공하는것.
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("dddd", MemberService.class));
        // -> 이후의 로직이 실행되면 NoSuchBeanDefinitionException이 예외가 터져야 한다는 조건.
    }
}
