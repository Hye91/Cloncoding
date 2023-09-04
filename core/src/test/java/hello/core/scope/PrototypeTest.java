package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        //AnnotationConfigApplicationContext에 PrototypeBean을 등록하면 componentScan처럼 등록해서 동작한다.

        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);

        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close();

        prototypeBean1.destroy();
        prototypeBean2.destroy(); //닫아줘야할때는 수동으로 직접 호출을 해줘야한다.
    }

    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
