package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1") //이건 값을 넘겨줘야하므로 post방식을 써야한다.
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        //inputStream은 byte 코드를 반환한다. byte 코드를 우리가 읽을 수 있는 문자(String)로 보려면 문자표
        //(Charset)를 지정해주어야 한다. 여기서는 UTF_8 Charset을 지정해주었다

        log.info("info messageBody={}",messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2") //이건 값을 넘겨줘야하므로 post방식을 써야한다.
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        //inputStream과 outStream을 이용하면 HttpServletRequest,HttpServletResponse 사용하지 않아도 된다.
        //HttpServlet의 모든 기능을 통으로 다 필요한 것이 아니기때문에 이렇게 사용할 수 있다.
        //ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        //inputStream은 byte 코드를 반환한다. byte 코드를 우리가 읽을 수 있는 문자(String)로 보려면 문자표
        //(Charset)를 지정해주어야 한다. 여기서는 UTF_8 Charset을 지정해주었다

        log.info("info messageBody={}",messageBody);

        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3") //이건 값을 넘겨줘야하므로 post방식을 써야한다.
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        //String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        //이런 stream의 코드까지도 스프링이 지원해주면 좋겠다고 하면 httpEntity의 httpMessageConverter기능을 지원받을 수 있다.
        String messageBody = httpEntity.getBody();
        log.info("info messageBody={}",messageBody);

        //responseWriter.write("ok");
        return new HttpEntity<>("ok");
        //void대신 반환 타입 또한 httpEntity의 방식으로 바꿔서 사용가능.
    }
}
