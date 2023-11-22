package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //hashMap : key와 value로 값을 관리함
    private static long sequence = 0L; // 아이디가 하나씩 증가하는 sequence를 사용

    //singletone으로 레포지토리 사용하기 위한 코드 (스프링을 사용하지 않을 경우)
    //singletone으로 사용할때는 private으로 생성자를 막아서 하나만 생성되도록 해줘야한다.
    private static final MemberRepository instance = new MemberRepository();
    public static MemberRepository getInstance(){
        return instance;
    }
    private MemberRepository(){
    }

    //save
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
    }

    public void cleatStore(){ //테스트 용으로 사용하는 건데 모든 정보 삭제
        store.clear();
    }
}
