package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(ClientBean.class,PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(2);

        //clientBean1 , clientBean2 모두 생성시점에서 주입된 PrototypeBean을 사용하게 된다. 새로 생성 X
        //이렇게 되면 프로토타입으로써의 의미가 없다(만들다음 책임없이 종료해야하는건데 만든 상태를 유지하게 되므로)
        //매번 ClientBean에서 PrototypeBean을 새로 생성해서 하는 방법은 따로 있다.
    }
    @Scope("singleton")
    static class ClientBean{
        private final PrototypeBean prototypeBean; //생성시점에 주입이 완료됨.

        @Autowired //의존관계 생성자 주입 , PrototypeBean 내놔!
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean; //스프링 컨테이너가 만들어서 넣어줌.
        }

        public int logic(){
            prototypeBean.addCount(); //생성시점에 주입된 그 PrototypeBean이 호출된다.
            int count = getCount();
            return count;
        }

        private int getCount() {
            return prototypeBean.getCount(); //ctrl + alt + n : Inline Variable
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
