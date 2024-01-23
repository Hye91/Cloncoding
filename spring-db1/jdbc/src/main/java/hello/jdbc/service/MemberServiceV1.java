package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;

@RequiredArgsConstructor
public class MemberServiceV1 {

    private final MemberRepositoryV1 repository;

    //계좌이체하는 로직
    public void accountTransfer(String fromId,String toId,int money) throws SQLException {

        Member fromMember = repository.findById(fromId); //보내는 회원
        Member toMember = repository.findById(toId); //받는 회원

        repository.update(fromId, fromMember.getMoney() - money);

        validation(toMember);
        repository.update(toId, toMember.getMoney() + money);


    }

    private static void validation(Member toMember) {
        //계좌 이체중 발생한 예외 로직
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("이체 중 예외 발생");
        }
    }
}
