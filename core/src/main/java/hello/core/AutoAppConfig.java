package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "hello.core.member", //이 위치에서부터 시작해서 찾아들어가게 설정할수있다.
        basePackageClasses = AutoAppConfig.class, // 지정한 클래스의 패키지를 탐색 시작 위치로 지정
        //만약 지정하지 않으면 @ComponentScan 이 붙은 설정 정보 클래스의 패키지가 시작 위치
        //지금은 AutoAppConfig 클래스의 패키지 위치인 hello.core; 부터 패키지 검색을 시작한다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //스캔을 해서 가져오는 과정에서 뺄걸 지정해준다. Configuration이 붙은걸 빼서 가져오도록 설정함.
        //AppConfig에 자동으로 등록되는 Configuration을 빼주는 것이다. (예제를 안전하게 유지하기 위해서 적용)
)
public class AutoAppConfig {

    //컴포넌트 스캔 : 스프링 빈을 자동으로 끌어올리는것
    // @Component가 붙은 클래스를 찾아서 자동으로 스프링 빈으로 등록을 한다.
}
