package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService; // = new MemberServiceImpl();
    OrderService orderService; // = new OrderServiceImple();

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){
        Long memberId = 1L;
        Member member = new Member(memberId,"memberA",Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

    @Test
    void fieldInjectionTest(){
        OrderService orderService1 = new OrderServiceImple();
        orderService1.createOrder(1L, "memberA", 10000);
        //.NullPointerException 직접 주입으로 설정을 하게되면 원하는 값을 변경할수 없게된다
        //임의의 DB를 만들어서 접근 Repository를 바꾸고 싶을때 변경할수 없게 되는것.
    }
}
