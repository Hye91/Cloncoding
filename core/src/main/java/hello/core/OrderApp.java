package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImple;

public class OrderApp {

    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImple();

        //회원가입
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        //주문
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order); //toSting으로 출력된다.
        System.out.println("order.calculatePrice = " + order.calculatePrice());

    }
}
