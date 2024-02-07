package hello.jdbc.repository;

import hello.jdbc.domain.Member;

import java.sql.SQLException;

public interface MemberRepositoryEx {
    //interface에 선언되어 있는 exception을 구현체에서 해결할수 있다.
    Member save(Member member) throws SQLException;
    //인터페이스에서 상위 예외를 던져두면 구현체에서는 그거와 같거나 하위 예외들을 커버할수있다.(체크예외에 대한 내용)
    Member findById(String memberId) throws SQLException;
    void update(String memberId, int money) throws SQLException;
    void delete(String memberId) throws SQLException;

}
