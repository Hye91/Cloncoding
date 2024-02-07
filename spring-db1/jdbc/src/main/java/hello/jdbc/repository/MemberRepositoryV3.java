package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

/**
 *트랜잭션 -트랜잭션 매니저
 * DataSourceUtils.getConnection() : 동기화 매니저가 관리하는 트랜잭션의 경우 매니저가 반환
 * DataSourceUtils.releaseConnection() 사용 : 바로 닫는것이 아닌 동기화로 관리하는 트랜잭션이 있으면 남겨둔다
 * -> 트랜잭션이 종료되고 commit 또는 Rollback이 되는 상황에서 종료되게 된다
 * 트랜잭션 동기화 매니저 : 같은 트랜잭션을 유지시켜준다
 * 만약, 매니저가 관리하지 않는 트랜잭션의 경우, 종료 또는 새로 생성한다
 */
@Slf4j
public class MemberRepositoryV3{

    //dataSource 사용 위해서 의존관계 주입
    private final DataSource dataSource;

    public MemberRepositoryV3(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Member save(Member member) throws SQLException {
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
            log.error("db error", e);
            throw e; //예외가 터지면 log로 예외를 확인하고 밖으로 던지기
        } finally {
            close(con,pstmt,null);
            //try에서 오류가 터져버리면 close에 도달하지 못하고 예외로 빠질수 있게 된다.
            //그렇기 때문에 항상 close가 되도록 finally에서 close해야한다.
        }

    }

    //조회
    public Member findById(String memberId) throws SQLException {
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
            log.error("db error", e);
            throw e;
        } finally {
            close(con,pstmt,rs);
        }
    }

    //조회2 -> con을 parameter로 받아와서 동기화하는 방식은 이제
    //DataSoursrUtils를 이용해서 해결되었으므로 사용하지 않는다
//    public Member findById(Connection con,String memberId) throws SQLException {
//        String sql = "select * from member where member_id = ?";
//
//        //try-catch문의 finally 문에서 선언을 해야하므로 밖에서 초기화를 시켜두는것
////        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null; //selectQuery의 결과를 담고 있는 통
//
//        try {
////            con = getConnection(); //parameter로 받아온 con을 사용해야하므로 getConnection으로 받아오지 않는다.
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1,memberId);
//
//            rs = pstmt.executeQuery();//조회시에 사용함, Update를 사용하면 data변경시에 사용함
//
//            //rs에서 값을 꺼내기
//            //rs내부에는 커서라는게 있는데 이걸 지금 데이터가 위치한 지점으로옮겨줘야한다.
//            // -> rs.next()를 하면 실제 데이터가 위치한 곳부터 시작하게 된다
//            if(rs.next()){
//                Member member = new Member();
//                member.setMemberId(rs.getString("member_id"));
//                member.setMoney(rs.getInt("money"));
//                return member;
//            } else { //data가 없는 경우
//                throw new NoSuchElementException("member not found memberId=" + memberId);
//            }
//        } catch (SQLException e) {
//            log.error("db error", e);
//            throw e;
//        } finally {
//            JdbcUtils.closeResultSet(rs);
//            JdbcUtils.closeStatement(pstmt);
////            JdbcUtils.closeConnection(con);
//            //서비스 로직이 있는 곳에서 connection을 시작하기 때문에
//            //종료 또한 서비스 로직이 있는 곳에서 끝나야한다!
//
//        }
//    }

    //수정
    public void update(String memberId,int money) throws SQLException {
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
            log.error("db error", e);
            throw e;
        } finally {
            close(con,pstmt,null);
        }
    }

    //수정2
//    public void update(Connection con,String memberId,int money) throws SQLException {
//        String sql = "update member set money=? where member_id=?";
//
////        Connection con = null;
//        PreparedStatement pstmt = null;
//
//        try {
////            con = getConnection();
//            pstmt = con.prepareStatement(sql);
//            /**
//             * sql의 parameter에 따른 순서로 번호줘야한다!
//             */
//            pstmt.setInt(1,money);
//            pstmt.setString(2,memberId);
//            int resultSize = pstmt.executeUpdate();
//            log.info("resultSize={}",resultSize);
//        } catch (SQLException e) {
//            log.error("db error", e);
//            throw e;
//        } finally {
////            JdbcUtils.closeResultSet(rs);
//            JdbcUtils.closeStatement(pstmt);
////            JdbcUtils.closeConnection(con);
//            //서비스 로직이 있는 곳에서 connection을 시작하기 때문에
//            //종료 또한 서비스 로직이 있는 곳에서 끝나야한다!
             //dataSourceUtils를 통해서 close를 사용하므로 더이상 이 방식의 update는 필요없다.
//        }
//    }

    //삭제
    public void delete(String memberId) throws SQLException {
        String sql = "delete from member where member_id=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,memberId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            log.error("db error={}",e);
            throw e;
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
