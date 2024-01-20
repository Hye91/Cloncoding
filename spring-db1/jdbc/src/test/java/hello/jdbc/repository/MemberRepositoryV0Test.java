package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.NoSuchMessageException;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();
    @Test
    void crud() throws SQLException {

        //save
        Member member = new Member("memberV100", 10000);
        repository.save(member);

        //조회 FindById
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember={}",findMember);

        log.info("member != findMember {}", member == findMember);
        log.info("member equal findMember {}", member.equals(findMember));
        //@Data가 지원하는 EqualsAndHashCode를 통해서 각각 다른 위치에서 호출되서 다른 참조값을 갖는 경우에도
        //클래스 내의 모든 필드를 고려하여 equals()와 hashCode() 메소드를 생성합니다.
        //이것은 객체의 모든 필드가 동일하다면 객체를 동등하다고 간주하게 만들어줍니다.
        //즉, 객체의 각 필드 값이 동일하다면 두 객체는 equals() 메소드로 비교했을 때 같은 것으로 간주됩니다.
        assertThat(findMember).isEqualTo(member);

        //수정 money를 10000 -> 20000
        repository.update(member.getMemberId(),20000);
        //검증
        Member updatedMember = repository.findById(member.getMemberId());
        assertThat(updatedMember.getMoney()).isEqualTo(20000);

        //삭제
        repository.delete(member.getMemberId());
        //Member deletedMember = repository.findById(member.getMemberId());
        //이미 삭제된 회원이므로 조회가 되지 않는다! -> NoSuchElementException이 터지게 된다
        //그럼 검증도 NoSuchElementException이 터지는지로 확인하면 된다?
        assertThatThrownBy(()-> repository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);

    }

}