package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class MemberRepositoryV1Test {

    MemberRepositoryV1 repository;

    //MemberRepositoryV0 repository = new MemberRepositoryV0();

    @BeforeEach //테스트가 실행되기 전에 실행되는것
    void beforeEach(){
        //기본 DriverManager - 항상 Connection 획득
        //V0에서는 직접 만든 DBConnectionUtil을 통해서 getConnection을 받아오고 활용
        //V1에서는 dataSource를 활용해서 connection을 테스트 시작전에 받아오는걸로 사용.
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        //이렇게 하면 매번 새로운 connection을 만들어서 사용하므로 성능이 느려질수 있다

        //HikariPool Connection 사용
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        repository = new MemberRepositoryV1(dataSource);
    }
    @Test
    void crud() throws SQLException, InterruptedException {

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

        Thread.sleep(1000);

    }

}