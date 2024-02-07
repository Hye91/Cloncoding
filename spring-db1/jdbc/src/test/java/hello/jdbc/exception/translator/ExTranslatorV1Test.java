package hello.jdbc.exception.translator;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class ExTranslatorV1Test {
    @Slf4j
    @RequiredArgsConstructor
    static class Service{
        private final Repository repository;

        void create(String memberId){ //아이디가 중복인 경우 DB error코드를 받아서 뒤에 숫자를 붙이는 로직
            try {
                repository.save(new Member(memberId,0));
                log.info("saveId={}",memberId);
            } catch (MyDuplicateKeyException e){
                log.info("키 중복, 복구 시도");
                //키 생성

            }
        }

        private String generateId(String memberId){

        }
    }

    @RequiredArgsConstructor//DB 예외를 전환해보는 테스터
    static class Repository{
        private final DataSource dataSource;

        public Member save(Member member){
            String sql = "insert into member(member_id, money) values(?,?)";
            Connection con = null;
            PreparedStatement pstmt = null;

            try {
                con = dataSource.getConnection();
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, member.getMemberId());
                pstmt.setInt(2,member.getMoney());
                pstmt.executeUpdate(); //DB에 저장되면서 트랜잭션 실행일건데
                return member;
            } catch (SQLException e){
                //더이상 exception을 밖으로 던지지 않고 DB 에러코드 확인해보기
                if(e.getErrorCode()==23505){
                    throw new MyDuplicateKeyException(e); //기존의 코드를 담아서 보내야한다.
                    //이 예외를 service에서 잡아서 복구를 할수 있게된다
                }
            } finally {
                JdbcUtils.closeStatement(pstmt);
                JdbcUtils.closeConnection(con);
            }
        }



    }
}
