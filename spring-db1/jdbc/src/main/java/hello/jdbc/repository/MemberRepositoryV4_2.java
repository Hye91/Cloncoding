package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

/**
 * SQLExceptionTranslatro 추가
 */
@Slf4j
public class MemberRepositoryV4_2 implements MemberRepository{

    //dataSource 사용 위해서 의존관계 주입
    private final DataSource dataSource;

    //예외처리를 위해서 translator 추가
    private final SQLExceptionTranslator exTranslator; //인터페이스

    public MemberRepositoryV4_2(DataSource dataSource) {

        this.dataSource = dataSource;
        //구현체를 생성자 주입 받는다
        //datasource를 파라미터로 넘기는 이유는 어떤 DB를 쓰는지 정보를 알아서 넘기기때문이다
        //org.springframework.jdbc.support.SQLExceptionTranslator;
        //Spring을 통해서 예외를 처리하는 것이므로 각각의 DB에 따른 예외에 의존적이지 않게된다.
        this.exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }
    @Override
    public Member save(Member member) /*throws SQLException*/ {
        String sql = "insert into member(member_id,money) values (?, ?)";

        Connection con = null; //연결
        PreparedStatement pstmt = null; //DB에 query를 보낸다, PreparedStatement : parameter를 binding할수 있는것

        try {
            //connection 가져오는 코드
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());//sql문 내의 ?에 parameterBinding을 한다
            pstmt.setInt(2,member.getMoney());
            pstmt.executeUpdate(); //준비된 DB에 저장되면서 실행되게 한다, data 등록, 수정, 삭제는 executeUpdate 조회는 executeQuery
            return member;
        } catch (SQLException e) {
            //throw new MyDbException(e); //기존의 예외를 담아주고, 더이상 체크 예외 아니므로 throws 제거

            //translator 사용해서 예외 던져주기
            throw exTranslator.translate("save",sql,e);
        } finally {
            close(con,pstmt,null);
            //try에서 오류가 터져버리면 close에 도달하지 못하고 예외로 빠질수 있게 된다.
            //그렇기 때문에 항상 close가 되도록 finally에서 close해야한다.
        }

    }

    @Override //조회
    public Member findById(String memberId) /*throws SQLException*/ {
        String sql = "select * from member where member_id = ?";

        //try-catch문의 finally 문에서 선언을 해야하므로 밖에서 초기화를 시켜두는것
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; //selectQuery의 결과를 담고 있는 통

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,memberId);

            rs = pstmt.executeQuery();//조회시에 사용함, Update를 사용하면 data변경시에 사용함

            //rs에서 값을 꺼내기
            //rs내부에는 커서라는게 있는데 이걸 지금 데이터가 위치한 지점으로옮겨줘야한다.
            // -> rs.next()를 하면 실제 데이터가 위치한 곳부터 시작하게 된다
            if(rs.next()){
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else { //data가 없는 경우
                throw new NoSuchElementException("member not found memberId=" + memberId);
            }
        } catch (SQLException e) {
//            throw new MyDbException(e);
            throw exTranslator.translate("findById",sql,e);
        } finally {
            close(con,pstmt,rs);
        }
    }

    @Override //수정
    public void update(String memberId,int money) /*throws SQLException*/ {
        String sql = "update member set money=? where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            /**
             * sql의 parameter에 따른 순서로 번호줘야한다!
             */
            pstmt.setInt(1,money);
            pstmt.setString(2,memberId);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize={}",resultSize);
        } catch (SQLException e) {

//            throw new MyDbException(e);
            throw exTranslator.translate("update",sql,e);
        } finally {
            close(con,pstmt,null);
        }
    }


    @Override //삭제
    public void delete(String memberId) /*throws SQLException*/ {
        String sql = "delete from member where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
//            throw new MyDbException(e);
            throw exTranslator.translate("delete",sql,e);
        } finally {

            close(con,pstmt,null);
        }

    }

    private void close(Connection con, Statement stmt/*sql을 그대로 넣는것*/, ResultSet rs){
        //PreparedStatement는 Statement를 상속받은거라서 더 기능이 많다
        //PreparedStatement, Connection을 close 해줘야한다 ! 시작과는 역순 방향으로 close해줘야함

        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        //주의! 트랜잭션 동기화 사용하기위해서 DataSourceUtils 사용
        //DataSourceUtils를 통해서 con을 끊는게 아니고 pool에 다시 돌려주는 방식으로 진행
        //트랜잭션 동기화 매니저를 통해서 con을 돌려주는 방식
        //JdbcUtils.closeConnection(con);
        DataSourceUtils.releaseConnection(con,dataSource);

    }

    private Connection getConnection() throws SQLException {
        //주의! 트랜잭션 동기화 사용하기위해서 DataSourceUtils 사용
        Connection con = DataSourceUtils.getConnection(dataSource);
        //Connection con = dataSource.getConnection();
        //더이상 DataSource를 통해서 직접 getConnection하지 않는다.
        //DataSourceUtils를 통해서 con을 가져와서 트랜잭션 동기화 매니저를 통해서 con을 관리하게 된다
        log.info("get connection={}, class={}",con, con.getClass());
        return con;
    }
}
