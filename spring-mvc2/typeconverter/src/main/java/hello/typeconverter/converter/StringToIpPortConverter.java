package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {
    // "127.0.0.1:8080"으로 String으로 들어온걸 IpPort 객체로 바꾸는 경우의 예제
    @Override
    public IpPort convert(String source) {
        log.info("convert source={}", source);
        //"127.0.0.1:8080"을 parcing해야하므로 :을 기준으로 잘라준다
        String[] split = source.split(":");

        String ip = split[0];
        int port = Integer.parseInt(split[1]); //port는 Int형태로 받는다
        return new IpPort(ip,port);
    }
}
