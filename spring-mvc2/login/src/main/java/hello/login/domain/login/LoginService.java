package hello.login.domain.login;

import hello.login.domain.member.Member;
import hello.login.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService { //로그인의 절차가 맞게 되는지를 구현하는 핵심 비즈니스로직

    private final MemberRepository memberRepository;

    /**
     *
     * @return null이면 로그인 실패
     */
    public Member login(String loginId, String password){
//        Optional<Member> findMemberOptional = memberRepository.findByLoginId(loginId);
//        Member member = findMemberOptional.get();//Optional에서는 get을 사용하면 안에가 꺼내져 나오게 된다.
//        if(member.getPassword().equals(password)){
//            return member;
//        } else {
//            return null;
//        }

        //java 8 Optional Stream
        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);

    }


}
