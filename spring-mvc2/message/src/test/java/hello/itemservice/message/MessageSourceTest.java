package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource ms;

    @Test
    void helloMessage(){

        String result = ms.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode(){ //코드가 없는 경우의 테스트
            assertThatThrownBy(()->ms.getMessage("no-code",null,null))
                    .isInstanceOf(NoSuchMessageException.class);
    }
}
