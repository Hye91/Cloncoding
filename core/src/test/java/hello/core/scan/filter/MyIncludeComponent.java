package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //클래스 레벨에 붙는다?
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent { //컴포넌트 스캔에 포함시킬것
}
