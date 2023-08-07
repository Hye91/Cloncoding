package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*; //alt + enter : static import
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    //RateDiscountPolicy가 정말 10% 할인을 하는지 테스트
    RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o(){
        //given 새로운 회원이 가입을 하고 VIP일때의 조건이 주어짐
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        //when 이 회원을 넘기고, 가격이 10000인 경우
        int discount = rateDiscountPolicy.discount(member, 10000);
        //then 할인 금액이 1000원인가?
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야한다.")
    void vip_x(){
        //given
        Member member = new Member(1L, "memberBasic", Grade.BASIC);
        //when
        int discount = rateDiscountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0);
    }
}