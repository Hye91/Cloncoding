package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //1. ThreadA : A사용자가 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        //2. ThreadB : B사용자가 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        //3. ThreadA : A사용자가 주문금액 조회
//        int price = statefulService1; //10000원
//        System.out.println("price = " + price); //사용자 A가 주문을 하고 조회를 하려는 사이 B가 주문을 한 경우 price는 20000원이 된다.
        //statefulService1에서 조회를 했는데도 20000원이 조회 된다!
        //같은 인스턴스를 사용하기 때문에 1, 2로 나눈다고 해서 price가 변경되게 된다.
        //statefulService1 == statefulService2의 참조값을 가지므로!!

        System.out.println("price = " + userAPrice); // 10000원으로 출력된다
        //지역변수는 공유되는게 아니므로 각각 A와 B에 지정된 값이 나오게 된다.
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

    }

    static class TestConfig{  //AppConfig 같은걸 지금 여기 임시로 만들어서 state로 사용
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}