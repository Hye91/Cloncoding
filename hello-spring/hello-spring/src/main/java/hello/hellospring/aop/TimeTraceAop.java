package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect //aop적용 시킬때 이 어노테이션 입력함.
public class TimeTraceAop { //@Component를 해서 사용해도 되지만 보통 스프링 Bean에 등록에서 쓰는걸 선호한다.

    @Around("execution(* hello.hellospring..*(..))") //공통관심사항에 적용시키는것, 패키지명 하위에 다 적용시키는 방법.
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("START : " + joinPoint.toString()); //어떤 메서드들을 콜하는지 알수 있다
        try {
            //다음 메서드로 진행
            return joinPoint.proceed(); //ctrl + alt + n :inline
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END : " + joinPoint.toString()+ " " + timeMs + "ms");
        }
    }
}
