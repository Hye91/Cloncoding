package hello.core.member;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Component //컴포넌트 스캔을 하기 위해서 적용시켜준다
//@Repository
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
