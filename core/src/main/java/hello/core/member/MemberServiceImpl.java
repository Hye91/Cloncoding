package hello.core.member;

public class MemberServiceImpl implements MemberService{
    //MemberServiceImpl은 MemberRepository와 MemoryMemberRepository 모두를 의존하게 된다.
    // DIP의 원칙이 잘 지켜지고 있는가?

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //ctrl + shift + enter : 자동완성
    //구현객체까지 설정을 해줘야 nullPointException이 일어나지 않는다.
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
