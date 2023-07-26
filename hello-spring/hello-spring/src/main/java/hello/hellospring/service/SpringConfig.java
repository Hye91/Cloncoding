package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //@Service, @Repository 사용하지 않고 직접 등록할때
public class SpringConfig {

    //직접 Bean을 등록할때
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
        //ctrl + p를 하면 뭘 넣어야하는지 알려준다.
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository(); //구현체 넣어주기
        //@Service, @Repository 사용(컴포넌트 스캔방식)하면
        //추후에 실제로 MemoryRepository를 DB로 바꿀때
        //여러 코드들을 변경해야하는데 직접 작성해서 등록을하면
        //SpringConfig만 변경하면 된다!
    }
}
