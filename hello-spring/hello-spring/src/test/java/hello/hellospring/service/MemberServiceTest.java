package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    //테스트를 할때 저장되는 데이터가 레포지토리에 누적이 되면
    //이름 중복으로 테스트의 오류가 터지게 된다. -> 레포지토리 클리어필요
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //테스트에서 진행하는 레포지토리는 new 레포지토리다.
    //다른 instance 이므로 내용물이 달라질수 있다.(MemoryMemberRepository에 생성된
    // 레파지토리랑 서로 다른 레파지토리가 된다.) 같은 걸로테스트를 하는게 맞다..!
    //같은 인스턴스를 쓰게 하려면 멤버서비스에서 memberRepository를 외부주입으로 바꿔준다.

    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    } //실행이 될때마다 새로 레포지토리를 만들고, 그 레포지토리를
    //서비스에 넣어주고 실행하게 된다. 이렇게 하면 테스트도 같은 레포지토리를
    //이용해서 테스트를 할 수 있게 된다.

    @AfterEach
    public void afterEach(){

        memberRepository.clearStroe();
    }

    @Test
    void join() {
        //given 상황이 주어졌을때
        Member member = new Member();
        member.setName("hello");

        //when 이걸 실행했을때
        Long saveId = memberService.join(member);
        //return에 대한걸 완성해준다. ctrl + alt + v


        //then 결과가 이렇게 나와야한다.

        //우리가 저장한게 레포지토리에 저장된게 맞는지를 찾고 싶은것
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //이름 중복인 경우의 예외도 테스트를 해보는게 중요하다
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");


        Member member2 = new Member();
        member2.setName("spring");

        //when
//        memberService.join(member1);
//        memberService.join(member2);
            //이런경우 예외가 터져야한다.

//        memberService.join(member1);
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
//        }
        //try catch 문을 말고 다른 방법
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //람다 표현식을 통해서 실행시 위와같은 예외가 터져야한다.

        //오류 메시지 동일인지 확인하는 코드
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}