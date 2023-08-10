package hello.core.benaFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames(); //빈으로 정의된 이름 등록하기, 스프링에 등록된 모든 이름 조회
        for (String beanDefinitionName/*Key*/ : beanDefinitionNames) { //iter + tab : 리스트나 배열이 있으면 자동으로 for문 만들어준다
            Object bean/*Value*/ = ac.getBean(beanDefinitionName);//정해진 빈 꺼내오기, 정해진 빈 조회
            System.out.println("name = " + beanDefinitionName + ", object = " + bean);
            //soutv : 변수명 찍어주기 , soutm : 메서드 이름 찍어주기
        }
    }
    //ctrl + d = 코드 동일한거 복사하기
    @Test
    @DisplayName("애플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName/*Key*/ : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);/*빈에 대한 정보들, 메타데이터*/

            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                //스프링 내부에서 일어나는 일을 위해서 등록한 빈이 아니라 APPLICATION의 개발을 위해 등록한 빈을 찾아와준다
                Object bean/*Value*/ = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + ", object = " + bean);
            }
        }
    }
}
