package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

/**
 * 트랜잭션 - connection parameter 전달 방식 동기화
 */
@Slf4j
class MemberServiceV2Test {

    public static final String MEMBER_A = "memberA";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    private MemberRepositoryV2 repository;
    private MemberServiceV2 service;

    @BeforeEach
    void before(){ //dataSource를 활용한 connection
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        repository = new MemberRepositoryV2(dataSource);
        service = new MemberServiceV2(dataSource,repository);
    }

    @AfterEach //테스트가 시행된 이후 DB에 남은 데이터를 한번 삭제시킨다
    void after() throws SQLException {
        repository.delete(MEMBER_A);
        repository.delete(MEMBER_B);
        repository.delete(MEMBER_EX);
    }

    @Test
    @DisplayName("정상이체")
    void accountTransfer() throws SQLException {
        //given
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_B, 10000);
        repository.save(memberA);
        repository.save(memberB);
        //when
        log.info("START EX");
        service.accountTransfer(memberA.getMemberId(),memberB.getMemberId(),2000);
        log.info("END EX");

        //then
        Member findMemberA = repository.findById(memberA.getMemberId());
        Member findMemberB = repository.findById(memberB.getMemberId());

        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(8000);
        Assertions.assertThat(findMemberB.getMoney()).isEqualTo(12000);
    }

    @Test
    @DisplayName("이체 중 예외 발생")
    void accountTransferEx() throws SQLException {
        //given
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberEx = new Member(MEMBER_EX, 10000);
        repository.save(memberA);
        repository.save(memberEx);
        //when
        Assertions.assertThatThrownBy(()->service.accountTransfer(memberA.getMemberId(),memberEx.getMemberId(),2000))
                .isInstanceOf(IllegalStateException.class);

        //autoCommit mode를 끄지 않고(트랜잭션 시작하지 않고) Test를 돌렸기때문에
        //자동으로 commit이 되고 있는 Test.그래서 A가 update되고 나서 ex인 경우에는 exception이 터지는 상황이므로
        //memberA에서는 돈이 빠져나갔지만 ex에서는 돈이 빠져나가지 않아서 돈이 그대로 유지되게 된다.

        //then
        Member findMemberA = repository.findById(memberA.getMemberId());
        Member findMemberB = repository.findById(memberEx.getMemberId());

        Assertions.assertThat(findMemberA.getMoney()).isEqualTo(10000);
        //service의 경우 ex가 터지게되면 rollback이 되게된다. 그러므로 초기 상태로 돌아가는 것
        //A의 돈은 10000으로 확인해야 맞는 로직이 된다.
        Assertions.assertThat(findMemberB.getMoney()).isEqualTo(10000);
    }


}