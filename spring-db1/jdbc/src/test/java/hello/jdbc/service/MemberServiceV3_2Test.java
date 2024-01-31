package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

/**
 * 트랜잭션 - 트랜잭션 탬플릿을 사용
 */
@Slf4j
class MemberServiceV3_2Test {

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    private MemberRepositoryV3 memberRepository;
    private MemberServiceV3_2 memberService;

    @BeforeEach
    void before(){ //dataSource를 활용한 connection
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        memberRepository = new MemberRepositoryV3(dataSource);

        //MemberServiceV3_1에는 이제 transactionManager를 사용하므로 주입을 받아야한다.
        //JDBC를 사용하므로 맞는 DataSourceTransactionManager 주입받음
        PlatformTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        //트랜잭션 매니저가 dataSorce를 통해서 conncetion을 만들게된다.
        //jdbc가 아닌 다른 DB를 사용하게 되는경우 의존관계 주입만 변경시키면 된다
        memberService = new MemberServiceV3_2(transactionManager,memberRepository);
    }

    @AfterEach //테스트가 시행된 이후 DB에 남은 데이터를 한번 삭제시킨다
    void after() throws SQLException {
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }

    @Test
    @DisplayName("정상이체")
    void accountTransfer() throws SQLException {
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
    void accountTransferEx() throws SQLException {
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
        //A의 돈은 10000으로 확인해야 맞는 로직이 된다.
        Assertions.assertThat(findMemberB.getMoney()).isEqualTo(10000);
    }


}