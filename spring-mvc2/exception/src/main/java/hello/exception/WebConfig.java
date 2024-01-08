package hello.exception;

import hello.exception.filter.LogFilter;
import hello.exception.interceptor.LogInterceptor;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/*","*.ico","/error","/error-page/**"/*오류페이지 경로를 직접 넣을수 있다*/);
        //interceptor은 filter처럼 dispatchType등을 고를수 없다
        //대신, excludePathPatterns을 통해서 관련된 경로를 다 제외시킬수 있다.
    }

    //@Bean
    public FilterRegistrationBean logFilter(){ //직접 만든 필터 config에 등록하는 과정
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST,DispatcherType.ERROR);
        //이 필터는 REQUEST ERROR 이 두가지의 경우에 호출이 된다
        return filterRegistrationBean;
    }
}
