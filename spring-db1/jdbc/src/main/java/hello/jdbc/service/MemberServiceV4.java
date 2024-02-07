package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * 예외 누수 문제 해결
 * SQL Exception 제거
 *
 * memverRepository interface에 의존하게 된다.
 */
@Slf4j
//@RequiredArgsConstructor
public class MemberServiceV4 {

    //비즈니스 로직을 수행하는 구간에서 트랜잭션이 일어나야한다.
    //비즈니스 구간에서 오류가 발생하면 전체를 커밋하든지 롤백해야하기 때문이다.

    private final MemberRepository memberRepository;

    public MemberServiceV4(MemberRepository memberRepository) { //생성자
        this.memberRepository = memberRepository;
    }

    //계좌이체하는 로직
    @Transactional //Annotation을 이용해서 transactional을 걸고 시작하겠다.
    public void accountTransfer(String fromId,String toId,int money){

        bizLogic(fromId,toId,money);

        };

    private void bizLogic(String fromId, String toId, int money){
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
