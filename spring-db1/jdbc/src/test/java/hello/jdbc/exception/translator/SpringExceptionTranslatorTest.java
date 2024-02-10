package hello.jdbc.exception.translator;

import hello.jdbc.connection.ConnectionConst;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.*;

@Slf4j
public class SpringExceptionTranslatorTest {

    DataSource dataSource;

    @BeforeEach
    void  init(){
        //JDBC가 제공하는 dirvermanager를 통해서 connection
        dataSource = new DriverManagerDataSource(URL,USERNAME,PASSWORD);

        //이후 repository,service의 경우에는 DBexcessException에 따른걸로 나누기 위해서 뺐나?
    }

    @Test
    void SQLExceptionErrorCode(){
        /**
         * Spring이 제공하는 DBExcess를 사용하지 않는 경우
         * 각각의 DB에 맞는 에러코드에 따른 에러를 우리가 직접 던져줘야한다.
         */
        String sql = "select bad grammer"; //잘못된 오류를 통해서 문법 오류가 발생된 경우

        try {
            Connection con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeQuery();
        } catch (SQLException e){
            assertThat(e.getErrorCode()).isEqualTo(42122); //h2 DB의 경우 에러코드
//            throw new BadSqlGrammarException()
            int errorCode = e.getErrorCode();
            log.info("errorCode={}", errorCode);
            log.info("error",e);

        }
    }

    /**
     * Spring이 제공하는 예외 변환기 사용
     */
    @Test
    void exceptionTranslator(){
        String sql = "select bad grammer";

        try{
            Connection con = dataSource.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.executeQuery();
        } catch (SQLException e){
            assertThat(e.getErrorCode()).isEqualTo(42122);
            //스프링 예외 계층을 한줄로 생성하게된다
            //리포지토리에서 if를 사용해서 각각 DB에 맞는 예외를 직접 써서 변환하는게 아니고 이 코드를 통해서 변환하게 된다.
            SQLErrorCodeSQLExceptionTranslator exTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
            //org.springframework.jdbc.BadSqlGrammarException
            DataAccessException resultEx = exTranslator.translate("select", sql, e);
            log.info("resultEx",resultEx);
            assertThat(resultEx.getClass()).isEqualTo(BadSqlGrammarException.class);
        }

    }
}
