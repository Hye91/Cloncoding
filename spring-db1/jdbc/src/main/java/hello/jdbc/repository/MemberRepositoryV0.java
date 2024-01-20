package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

/**
 * JDBC - DriverManager를 사용해서 저장
 */
@Slf4j
public class MemberRepositoryV0 {

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
            pstmt.executeUpdate(); //준비된데 DB에 저장되면서 실행되게 한다
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

    private void close(Connection con, Statement stmt/*sql을 그대로 넣는것*/, ResultSet rs){
        //PreparedStatement는 Statement를 상속받은거라서 더 기능이 많다
        //PreparedStatement, Connection을 close 해줘야한다 ! 시작과는 역순 방향으로 close해줘야함

        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                log.error("error", e);
            }
        }

        //코드의 안정성을 위해서 ex가 터지는 상황을 고려해야한다.
        //만약 코드 진행 과정에서 exception이 터진다면 이후 con을 close가 일어나지 않게된다.
        //그래서 그런 예외들을 처리하는 로직 구현해야한다.
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                log.error("error",e);
            }
        }
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                log.error("error", e);
            }
        }

    }

    private static Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
