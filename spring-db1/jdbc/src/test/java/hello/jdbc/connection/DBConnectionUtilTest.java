package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class DBConnectionUtilTest {

    @Test
    void connection(){
        //h2 Driver는 JdbcConnection을 제공한다.
        //DB가 바뀌어도 connection을 통해서 가져오므로 어떤 connection을 사용하는지에 대해서는 신경쓸 필요X
        Connection connection = DBConnectionUtil.getConnection();
        assertThat(connection).isNotNull();
    }
}