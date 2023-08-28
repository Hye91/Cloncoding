package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class); //빈 클래스를 스프링 빈 등록

    }

    //임의의 테스트 클래스
    static class TestBean{
        //스프링에 관리되지 않는 Member클래스를 가져와서 연결해보기
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        } //의존관계가 없기때문에 메서드 자체가 호출이 되지 않는다!

        @Autowired //@Nullable 사용
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        } //호출은 되나 null로 호출된다

        @Autowired //java 8의 Optional 사용
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }


    }
}
