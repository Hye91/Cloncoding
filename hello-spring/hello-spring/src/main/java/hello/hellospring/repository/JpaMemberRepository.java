package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
@Transactional //JPA는 모든 변경이 트랜잭션 안에서 실행 되어야한다.
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //jpa는 EntityManager로 모든 것이 동작한다.
    //build.gradle 에 jpa를 넣어주면 스프링 부트가 Entity Manager을 자동으로 생성해준다.
    //jpa를 쓰려면 Em을 주입을 받아야한다.
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //pk를 가지는 id는 이렇게 조회하면 된다.
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    //PK기반이 아닌 것을 찾을때는  JPQL이라는 것을 작성해서 찾아준다.

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = : name", Member.class)
                //Member 클래스의 인스턴스를 검색하는 쿼리를 실행하는데, name 파라미터를 사용하여 검색합니다.
                .setParameter("name", name)
                //'where m.name = name' 부분에서 name 파라미터의 값으로 입력된 name이 사용
                .getResultList();
                //결과를 List<Member> 형태로 반환
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
//        List<Member> result = em.createQuery("select m from Member", Member.class).getResultList();
//        return result; ctrl + alt + n 하면 두개의 같은 result 갖는 값이 합쳐진다. (inline만들기)
        return em.createQuery("select m from Member", Member.class).getResultList();
                                    //객체(엔티티)를 대상으로 쿼리를 보낼수 있다.


    }
}
