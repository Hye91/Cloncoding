package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class); //스프링 빈 등록
        //ConfigurableApplicationContext이 AnnotationConfigApplicationContext의 상위 인터페이스
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    //데이터베이스 커넥션 풀이나, 네트워크 소켓처럼 애플리케이션 시작 시점에 필요한 연결을 미리 해두고,
    //애플리케이션 종료 시점에 연결을 모두 종료하는 작업을 진행하려면, 객체의 '초기화'와 '종료 작업'이 필요하다
    //NetworkClient라는 네트워크를 만들어두고 테스르를 진행

    @Configuration
    static class LifeCycleConfig{

        @Bean(initMethod = "init", destroyMethod = "close") //빈 등록 방식으로 연결,연결끊을때 지정하기
        //destroyMethod()에는 디폴트 값이 있다 -> default AbstractBeanDefinition.INFER_METHOD
        //INFER_METHOD : close , shutdown 라는 이름의 메서드를 자동으로 호출
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
