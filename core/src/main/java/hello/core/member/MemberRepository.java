package hello.core.member;

public interface MemberRepository {

    void save(Member member); //회원저장

    Member findById(Long memberId); //아이디를 통해서 멤버찾기
}
