package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * 트랜잭션 - parameter을 연동하고 pool을 고려한 종료
 * DB와의 트랜잭션 관리를 위해서 DataSource를 사용한다.
 */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    //비즈니스 로직을 수행하는 구간에서 트랜잭션이 일어나야한다.
    //비즈니스 구간에서 오류가 발생하면 전체를 커밋하든지 롤백해야하기 때문이다.
    private final DataSource dataSource; //connection을 위해서 dataSource사용
    private final MemberRepositoryV2 repository;

    //계좌이체하는 로직
    public void accountTransfer(String fromId,String toId,int money) throws SQLException {
        //transaction 시작
        Connection con = dataSource.getConnection();

        try {
            con.setAutoCommit(false); //트랜잭션이 시작된다
            //순수 비즈니스 로직 수행
            bizLogic(con, fromId, toId, money);

            //정상으로 로직 수행 이후
            con.commit();

        } catch (Exception e){
            con.rollback(); //실패시, rollback을 하게 된다.
            throw new IllegalStateException(e);
        } finally { //release를 해줘야한다? connection 정리해줘야한다.
            release(con);
        }

        //commit 또는 rollback
    }

    private void bizLogic(Connection con, String fromId, String toId, int money) throws SQLException {
        //ctlr + F6 을 하면 parameter의 순서를 바꿀수 있다.
        //con을 parameter로 넘겨서 다 같은 connection을 활용하게 된다.
        Member fromMember = repository.findById(con, fromId); //보내는 회원
        Member toMember = repository.findById(con, toId); //받는 회원

        repository.update(con, fromId, fromMember.getMoney() - money);

        validation(toMember);

        repository.update(con, toId, toMember.getMoney() + money);
    }

    private static void release(Connection con) {
        if(con != null){
            try {
                //con.setAutoCommit(false);인 상태로 pool에 돌아가면 다시 conncetion을 가져오는 경우 false인 상태가 유지된다
                //그래서 true로 바꾼 다음 pool에 돌려줘야한다. connection pool을 고려해서 종료
                con.setAutoCommit(true);
                con.close();

            } catch (Exception e){
                log.info("error", e);
            }
        }
    }

    private static void validation(Member toMember) {
        //계좌 이체중 발생한 예외 로직
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }
}
