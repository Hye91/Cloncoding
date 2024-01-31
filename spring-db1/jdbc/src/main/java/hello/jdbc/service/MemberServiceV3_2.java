package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * 트랜잭션 - 트랜잭션 템플릿
 */
@Slf4j
//@RequiredArgsConstructor
public class MemberServiceV3_2 {

    //비즈니스 로직을 수행하는 구간에서 트랜잭션이 일어나야한다.
    //비즈니스 구간에서 오류가 발생하면 전체를 커밋하든지 롤백해야하기 때문이다.

    //private final PlatformTransactionManager transactionManager;
    //반복되는 try catch를 tempalte로 활용하기위해서 transactionTemplate를 활용
    private final TransactionTemplate txTemplate;
    private final MemberRepositoryV3 memberRepository;

    public MemberServiceV3_2(PlatformTransactionManager transactionManager, MemberRepositoryV3 memberRepository) { //생성자
        this.txTemplate = new TransactionTemplate(transactionManager);
        //txTemplate를 사용하려면 transactionManager가 필요하기 때문에 위와 같이 사용한다.
        //transactionManager 의존관계를 주입받고 내부에서 txTemplate를 사용한다.
        this.memberRepository = memberRepository;
    }

    //계좌이체하는 로직
    public void accountTransfer(String fromId,String toId,int money) throws SQLException {

        //우리 메서드는 반환값이 void인 상태이므로 without을 사용한다
        //밑의 코드를 통해서 트랜잭션이 시작을 하고 비즈니스 로직을 수행하게 된다. 이후 커밋 또는 롤백이 수행됨
        txTemplate.executeWithoutResult((status/*parameter로 넘겨준다*/) -> {
            try {
                bizLogic(fromId,toId,money);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
        //transaction 시작
//        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
//
//        try {
//            //con.setAutoCommit(false); //트랜잭션이 시작된다 -> 트랜잭션 매니저로 관리하게 바꿈
//            //순수 비즈니스 로직 수행
//            bizLogic(fromId, toId, money);
//
//            //정상으로 로직 수행 이후
//            transactionManager.commit(status);
//
//        } catch (Exception e){
//            //실패시, rollback을 하게 된다.
//            transactionManager.rollback(status);
//            throw new IllegalStateException(e);
//        }
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        //ctlr + F6 을 하면 parameter의 순서를 바꿀수 있다.
        //con을 parameter로 넘겨서 다 같은 connection을 활용하게 된다.
        Member fromMember = memberRepository.findById(fromId); //보내는 회원
        Member toMember = memberRepository.findById(toId); //받는 회원

        memberRepository.update(fromId, fromMember.getMoney() - money);

        validation(toMember);

        memberRepository.update(toId, toMember.getMoney() + money);
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
