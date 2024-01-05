package hello.login;

import hello.login.web.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class WebConfig {

    @Bean
    public FilterRegistrationBean logFilter(){
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter()); //직접 만든 필터를 필터에 등록해준다
        filterRegistrationBean.setOrder(1); // 필터의 순서 1번으로 지정
        filterRegistrationBean.addUrlPatterns("/*"); //어떤 url패턴에 필터를 적용시킬것인지 적용

        return filterRegistrationBean;
    }
}
