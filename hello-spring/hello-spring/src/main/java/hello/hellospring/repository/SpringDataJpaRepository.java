package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaRepository extends JpaRepository<Member,Long>, MemberRepository {
    //interface끼리는 implement가 아니다 상속을 받고, 다중 상속이 되니까 우리가 만들었던 MemberRepository까지 상속받음
    //JpaRepository를 상속받으면 Spring Data Jpa가 자동으로 구현체를 만들게 된다.
    //기본적인 메서드를 JpaRepository에서 제공을 해주게 된다.
    @Override
    Optional<Member> findByName(String name);

    //findByNameAndId(String name, Long Id) 이런 식으로 이름 만으로 구현을 완성시킬수 있다.
}
