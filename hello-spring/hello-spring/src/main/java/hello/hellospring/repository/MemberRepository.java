package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); //회원을 저장하면 저장된 회원반환
    //저장을 하면 저장소에 회원 저장
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    //Optional : null값을 그대로 반환하는 대신 optional을 통해 감싸서 반환
    List<Member> findAll(); //저장된 모든 회원 리스트 반환

}
