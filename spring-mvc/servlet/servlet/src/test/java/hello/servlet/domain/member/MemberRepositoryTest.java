package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberRepositoryTest {

    //MemberRepository memberRepository = new MemberRepository(); 이렇게하면 singletone이 깨지게 되서 오류가 뜬다
    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.cleatStore(); //테스트 끝날때마다 깔끔하게 초기화 하기 위함
        //테스트를 실행할때는 순서를 따르지 않기때문에 이전의 테스트 값이 저장되면 다음의 테스트에 영향을 미칠수 있다
        //이것을 예방하기 위해서 각 테스트가 끝날때마다 지우는걸 실행해주는게 좋다.
    }

    @Test
    void save(){
        //given
        Member member = new Member("hello",20);

        //when
        Member savedMember = memberRepository.save(member);

        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        Assertions.assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 = new Member("hi", 30);
        Member member2 = new Member("ho", 31);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> all = memberRepository.findAll();

        //then
        Assertions.assertThat(all.size()).isEqualTo(2);
        Assertions.assertThat(all).contains(member1,member2);
        }

    }
