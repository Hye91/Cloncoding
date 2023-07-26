package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{
    //인터페이스로 작성된 MemberRepository를 실제 구현

    private static Map<Long,Member> store = new HashMap<>();
    //Map : 키와 값을 쌍으로 저장하는 구조, 구현체가 HashMap
    private static long sequence =0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        //회원이 저장한 아이디가 아닌 임의로 지정되는 시스템 아이디
        // 멤버가 저장될때 sequence값을 하나 올려주는걸로 설정하고 시작.
        store.put(member.getId(), member);
        //HashMap에 저장할때 put사용, 꺼내고 싶을땐 get
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // store.get(id)을 이용해서 가져올수 있는데
        //null인 경우가 있을수 있다 (값이 없을때)
        //이때는 Optional을 통해 감싸서 가져온다.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values()
                .stream()//stream을 통해서 데이터를 연속적으로 처리
                .filter(member -> member.getName().equals(name))
                //(parameter list : 매개변수) -> {expression body : 수행연산}
                .findAny(); //필터링된 결과중 아무요소 하나 가져옴. null일경우
                //Optional.empty 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStroe(){
        store.clear(); //각 저장된 데이터를 지우는 메서드
        //테스트 할때 사용되게 된다
    }

}
