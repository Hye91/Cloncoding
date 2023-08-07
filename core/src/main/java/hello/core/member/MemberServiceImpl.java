package hello.core.member;

public class MemberServiceImpl implements MemberService{
    //MemberServiceImpl은 MemberRepository와 MemoryMemberRepository 모두를 의존하게 된다.
    // DIP의 원칙이 잘 지켜지고 있는가?

    private final MemberRepository memberRepository; // = new MemoryMemberRepository();
    //MemberServiceImpl은 이제 memberRepository인 추상에만 의존을 하게 된다.
    //구현체는 더이상 의존하지 않는다.

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } //생성자 외부 주입(DI). DIP 지키기 위해서 외부에서 주입을 해주고, AppConfig에서 관리하게 한다.

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
