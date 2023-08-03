package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository{

    //메모리 저장소니까 저장하는 공간을 만들어줘야한다.
    private static Map<Long,Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        //map저장할때는 put 사용
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        //map 가져올때는 get 사용
        return store.get(memberId);
    }
}
