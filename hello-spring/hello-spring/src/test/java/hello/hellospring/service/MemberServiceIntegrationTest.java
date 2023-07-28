package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;
@SpringBootTest
@Transactional //'test'를 실행할때 DB에 인서트쿼리를 하고 다 넣은 다음에 테스트가 끝나면 롤백을 해준다.(이전상태로 돌아감)
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    //테스트 할때는 필드 기반으로 인젝션 받는게 편하다.
    //MemoryMemberRepository에서 MemberRepository로 변경시키는데 SpringConfig에서
    //JdbcMemberRepository를 받기때문에 MemberRepository를 입력하게 된다.

//    @BeforeEach
//    public void beforeEach(){
//        memberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memberRepository);
//    } spring 컨테이너한테 이제 이 요청을 보내야하므로 직접 생성하지 않는다.

//    @AfterEach
//    public void afterEach(){
//
//        memberRepository.clearStroe();
//    }

    @Test
    void join() throws Exception {
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

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //람다 표현식을 통해서 실행시 위와같은 예외가 터져야한다.

        //오류 메시지 동일인지 확인하는 코드
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");

        //then
    }
}