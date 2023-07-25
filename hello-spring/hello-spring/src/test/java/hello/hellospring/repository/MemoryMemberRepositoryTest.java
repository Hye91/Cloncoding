package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStroe();
    }
    //같은 값을 각각의 테스트에 저장시 모든 테스트 돌때
    //중복이 되는 값이 지정되서 오류가 터질수 있다.
    //테스트는 서로 의존관계없이, 순서 상관없이 실행이 되어야 한다.
    //그래서 각 테스트가 끝나면 저장된데이터를 지우는 메서드 필요!

    @Test
    public void save(){
        Member member = new Member();
        member.setName("hi");

        //설정한 값 저장
        repository.save(member);

        //검증
        Member result = repository.findById(member.getId()).get();
        //findById의 반환 타입이 Optional이기때문에 이때 값을 가져오려면
        //get을 통해서 값을 가져올수 있다.
        //Assertions.assertEquals(member,result);
        //member의 값과 result의 값이 똑같은지 확인하는 기능.
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("didi");
        repository.save(member1);

        Member member2 = new Member(); //shift + f6 : 리네임
        member2.setName("didi2");
        repository.save(member2);

        //member1 과 member2가 회원가입이 된 상황.

        Member result = repository.findByName("didi").get();
        //.get을 사용하면 optional 한번 까서 가져올수 있다?
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("didi");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("didi2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}

