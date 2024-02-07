package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepository;
import hello.jdbc.repository.MemberRepositoryV4_1;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 예외 누수 문제 해결
 * SQL Exception 제거
 *
 * MemberRepository interface에 의존
 */
@Slf4j
@SpringBootTest
class MemberServiceV4Test {

    //Transactional AOP를 적용하려면 Spring 이 제공하는게 다 제공이 된 상태여야한다.
    //Spring Container를 사용하지 않으면 롤백이 적용되지 않게 된다.
    //그래서 직접 등록해서 사용했던 것들을 Spring Bean에 등록해서 사용해야한다.

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    @Autowired //의존관계 주입
    private MemberRepository memberRepository;
    @Autowired
    private MemberServiceV4 memberService;

    @TestConfiguration //test를 위해서 Springbean에 등록해야하는것들 지정해주는 클래스
    static class TestConfig{
        private final DataSource dataSource;

        public TestConfig(DataSource dataSource) {

            this.dataSource = dataSource;
        } //Spring에서 만들어준 dataSource를 주입해주게 된다.

        @Bean
        MemberRepository memberRepository(){
        //MemberRepository에 대한 구현체를 Bean으로 등록
            return new MemberRepositoryV4_1(dataSource);
        }

        @Bean
        MemberServiceV4 memberServiceV4(){

            return new MemberServiceV4(memberRepository());
        }
    }

    @AfterEach //테스트가 시행된 이후 DB에 남은 데이터를 한번 삭제시킨다
    void after() {
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }

    @Test
    void AopCheck(){
        log.info("memberService class={}",memberService.getClass());
        log.info("memberRepository class={}",memberRepository.getClass());
        Assertions.assertThat(AopUtils.isAopProxy(memberService)).isTrue();
        Assertions.assertThat(AopUtils.isAopProxy(memberRepository)).isFalse();
    }

    @Test
    @DisplayName("정상이체")
    void accountTransfer() {
        //given
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_B, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberB);
        //when
        log.info("START EX");
        memberService.accountTransfer(memberA.getMemberId(),memberB.getMemberId(),2000);
        log.info("END EX");

        //then
        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberB = memberRepository.findById(memberB.getMemberId());

        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(8000);
        Assertions.assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체 중 예외 발생")
    void accountTransferEx() {
        //given
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberEx = new Member(MEMBER_EX, 10000);
        memberRepository.save(memberA);
        memberRepository.save(memberEx);
        //when
        Assertions.assertThatThrownBy(()->memberService.accountTransfer(memberA.getMemberId(),memberEx.getMemberId(),2000))
                .isInstanceOf(IllegalStateException.class);

        //autoCommit mode를 끄지 않고(트랜잭션 시작하지 않고) Test를 돌렸기때문에
        //자동으로 commit이 되고 있는 Test.그래서 A가 update되고 나서 ex인 경우에는 exception이 터지는 상황이므로
        //memberA에서는 돈이 빠져나갔지만 ex에서는 돈이 빠져나가지 않아서 돈이 그대로 유지되게 된다.

        //then
        Member findMemberA = memberRepository.findById(memberA.getMemberId());
        Member findMemberB = memberRepository.findById(memberEx.getMemberId());

        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(10000);
        //service의 경우 ex가 터지게되면 rollback이 되게된다. 그러므로 초기 상태로 돌아가는 것
        //A의 돈은 10000으로 확인해야 맞는 로직이 된다. -> 이게 적용이 안되면 롤백이 되지 않은 상태라는 것
        Assertions.assertThat(findMemberB.getMoney()).isEqualTo(10000);
    }


}