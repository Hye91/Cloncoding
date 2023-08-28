package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImpleTest {

    @Test
    void createOrder(){
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L,"name", Grade.VIP));

        OrderServiceImple orderService = new OrderServiceImple(new MemoryMemberRepository(),new FixDiscountPolicy());
        //생성자 외부주입으로 설정하면 이 상태에서 컴파일 오류가 뜬다. 뭐가 필수로 들어가야하는값인지 알수 있고
        //이때 임의로 값을 넣어서 테스트를 할수 있게된다.
        Order order = orderService.createOrder(1L, "memberA", 10000);

        org.assertj.core.api.Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    } //수정자 외부주입으로 설정을 해두었기때문에 값을 설정을 해줘야한다. NullPointerException뜸
    //가짜 memorrRepository라도 만들어서 값을 넣어줘야한다.
}