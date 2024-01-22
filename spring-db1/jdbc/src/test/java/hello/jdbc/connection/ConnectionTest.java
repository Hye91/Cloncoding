package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        //connection 하나 얻기
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        log.info("connection={}, !class={}",con1,con1.getClass());
        log.info("connection={}, !class={}",con2,con2.getClass());

        }
    @Test
    void dataSourceDriverManager() throws SQLException {
        //DriverManager을 DataSource가 제공하는 추상화를 제공하지 않는다
        //그래서 Spring은 DriverManager도 Spring이 DataSource 추상화를 이용하도록 DriverManagerDataSource지원

        //DriverManagerDataSource - 항상 새로운 connection 연결 획득
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        //생성하는 시점에서 parameter를 사용하지만 사용하는 시점에서는 parameter를 사용하지 않는다
        useDataSource(dataSource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        //커넥션 풀링 : 히카리 풀 사용
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10); //pool사이즈 최대 지정
        dataSource.setPoolName("MyPool"); //pool이름 설정

        useDataSource(dataSource);
        Thread.sleep(1000); //pool에 추가하는 것까지 log로 확인하기 위해서 지연시간 추가
    }

    //실제 데이터 사용 -> parameter 필요로 하지 않는다
    private void useDataSource(DataSource dataSource) throws SQLException {

        //dataSource라는 iterface를 통해서 연결
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
//        Connection con3 = dataSource.getConnection();
//        Connection con4 = dataSource.getConnection();
//        Connection con5 = dataSource.getConnection();
//        Connection con6 = dataSource.getConnection();
//        Connection con7 = dataSource.getConnection();
//        Connection con8 = dataSource.getConnection();
//        Connection con9 = dataSource.getConnection();
//        Connection con10 = dataSource.getConnection();
//        Connection con11 = dataSource.getConnection();
//        Connection con12 = dataSource.getConnection();
        //Connection이 존재하지 않으면 HandOffQueue를 Polling 하면서 다른 Thread가 Connection을 반납하기를 기다립니다.
        //이를 지정한 TimeOut 시간까지 대기하다가 시간이 만료되면 Exception을 던집니다.

        log.info("connection={}, class={}",con1,con1.getClass());
        log.info("connection={}, class={}",con2,con2.getClass());
    }

}
