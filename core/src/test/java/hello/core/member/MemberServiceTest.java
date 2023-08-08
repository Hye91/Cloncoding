package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService; // = new MemberServiceImpl();
    //여기서 바로 AppConfig를 꺼내기 애매하다(?)

    @BeforeEach //테스트 실행하기전에 미리 실행을 해준다
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test //실행과 제어권을 junit이라는 프레임워크가 가져간다. -> 제어의 역전
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
