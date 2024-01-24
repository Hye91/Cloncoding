package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;

@RequiredArgsConstructor
public class MemberServiceV1 {

    //비즈니스 로직을 수행하는 구간에서 트랜잭션이 일어나야한다.
    //비즈니스 구간에서 오류가 발생하면 전체를 커밋하든지 롤백해야하기 때문이다.

    private final MemberRepositoryV1 repository;

    //계좌이체하는 로직
    public void accountTransfer(String fromId,String toId,int money) throws SQLException {
        //transaction 시작
        Member fromMember = repository.findById(fromId); //보내는 회원
        Member toMember = repository.findById(toId); //받는 회원

        repository.update(fromId, fromMember.getMoney() - money);

        validation(toMember);

        repository.update(toId, toMember.getMoney() + money);

        //commit 또는 rollback
    }

    private static void validation(Member toMember) {
        //계좌 이체중 발생한 예외 로직
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }
}
