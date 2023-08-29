package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component //컴포넌트 스캔을 하기 위해서 적용시켜준다
//@Qualifier("mainDiscountPolicy") //빈의 타입이 중복인 경우 사용하는 방법2
//@Primary //중복 타입의 여러개의 빈이 있을경우 primary가 있는걸 제일 우선순위로 잡히면서 의존관계 주입이 된다.
@MainDiscountPolicy //직접 만든 어노테이션을 통해서 Qualifier의 속성을 적용시킬수 있다.
//Qualifier의 경우 파라미터를 문자로 입력 받기때문에 혹여라도 잘못된경우 컴파일 오류를 잡을수 없다!
public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        //ctrl + shift + T = Test 클래스 만들어준다.
        if (member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
