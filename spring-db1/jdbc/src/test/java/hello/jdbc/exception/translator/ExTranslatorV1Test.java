package hello.jdbc.exception.translator;

import hello.jdbc.connection.ConnectionConst;
import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import hello.jdbc.repository.ex.MyDuplicateKeyException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ExTranslatorV1Test {

    Repository repository;
    Service service;

    @BeforeEach
    void init(){
        //DriverManagerDataSource을 이용해서 connection을 하는 방법
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        repository = new Repository(dataSource);
        service = new Service(repository);
    }

    @Test
    void dupilcateKeySave(){
        service.create("MyId");
        service.create("MyId"); //같은 아이디 저장 시도
    }
    @Slf4j
    @RequiredArgsConstructor
    static class Service{
        private final Repository repository;

        void create(String memberId){
            //아이디가 중복인 경우 DB error코드를 받는 MyDuplicateKeyException를 잡아서
            //새로운 아이디를 생성하게 하는 로직
            try {
                repository.save(new Member(memberId,0));
                log.info("saveId={}",memberId);
            } catch (MyDuplicateKeyException e){
                log.info("키 중복, 복구 시도");
                //키 생성
                String retryId = generateNewId(memberId);
                log.info("retryId={}",retryId);
                repository.save(new Member(retryId,0));
            } catch (MyDbException e){
                log.info("데이터 접근 계층 예외", e);
                throw e;
            }
        }

        private String generateNewId(String memberId){ //키 생성 메서드
            return memberId + new Random().nextInt(10000);
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
                throw new MyDbException(e);
            } finally {
                JdbcUtils.closeStatement(pstmt);
                JdbcUtils.closeConnection(con);
            }
        }
    }
}
