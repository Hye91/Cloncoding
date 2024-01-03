package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;



    //저장
    public Member save(Member member){
        member.setId(++sequence);
        log.info("save : member = {} ", member);
        store.put(member.getId(), member);
        return member;
    }

    //회원 1명 찾기(로그인 id말고 저장되어 DB에서 관리되어지는 id)
    public Member findById(Long id){
        return store.get(id);
    }

    //회원 id로 찾기
    public Optional<Member> findByLoginId(String loginId){
        //Optional은 Java 8에서 도입된 클래스로, 값이 존재할 수도 있고 존재하지 않을 수도 있는 컨테이너를 나타냄.

//        List<Member> all = findAll();
//        for (Member m : all) {
//            if(m.getLoginId().equals(loginId)){
//                return Optional.of(m);
//            }
//        }
//        return Optional.empty(); //값이 null인 경우 empty를 사용하게 한다

        return findAll().stream()/*findAll List를 stream으로 변환*/
                .filter(m -> m.getLoginId().equals(loginId))/*filter을 통해서 조건에 만족하는 값 걸러내기*/
                .findFirst(); //찾은값 중에서 제일 먼저 나오는값 가져온다

    }

    //회원 목록 조회
    public List<Member> findAll(){
        return new ArrayList<>(store.values());
        //map에 저장되어 있는 value값들을 (key제외) 리스트로 쭉 보여주는것
    }

    public void clearStore(){
        store.clear();
    }
}
