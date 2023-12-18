package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

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

    @Test
    void notFoundMessageCodeDefaultMessage(){
        String result = ms.getMessage("no-code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    void argumentMessage(){ //매개변수를 넘길때 어떻게 적용시키는지에 대한 방법
        //hello.name=안녕 {0}에서 {0}의 값을 new Object를 사용해서 값을 넣어줄수 있게된다.
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(message).isEqualTo("안녕 Spring");

    }

    @Test
    void defaultLang(){
        assertThat(ms.getMessage("hello",null,null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello",null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    void enLang(){
        assertThat(ms.getMessage("hello",null,Locale.ENGLISH)).isEqualTo("hello");
    }
}
