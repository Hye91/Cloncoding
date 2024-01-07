package hello.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {
    //오류가 발생할때 웹서버(톰캣)화면은 우리가 원하는 형태로 커스텀하기 위해서 생성하는 클래스
    //지정한 implements를 사용해야한다.
    @Override
    public void customize(ConfigurableWebServerFactory factory) {

        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");
        //404예외가 발생하면 /error-page/404 controller을 호출하라는 로직
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");

        ErrorPage errorPageEx = new ErrorPage(RuntimeException.class,"/error-page/500");
        //runtimeEx가 터지면 /error-page/500 controller을 호출하라는 로직
        //이때 RuntimeException의 자식 예외들도 모두 포함되어진다

        //에러 등록
        factory.addErrorPages(errorPage404,errorPage500,errorPageEx);

    }
}
