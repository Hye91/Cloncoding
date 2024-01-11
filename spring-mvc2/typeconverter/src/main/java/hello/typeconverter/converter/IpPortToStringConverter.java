package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class IpPortToStringConverter implements Converter<IpPort,String> {
    @Override
    public String convert(IpPort source) {
        log.info("convert source={}",source);
        //"127.0.0.1"과 8080인 IpPort객체를 parcing해야하므로 둘을 합쳐줘야하는데
        return source.getIp() + ":" + source.getPort();
    }
}
