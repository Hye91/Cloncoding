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
        //기존에는 직접 객체를 생성하고 했지만 AppConfig를 이용해서도 만들어보기
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();
//        MemberService memberService = new MemberServiceImpl(null);
//        OrderService orderService = new OrderServiceImple(null,null);

        //회원가입
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        //주문
        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = " + order); //toSting으로 출력된다.
        System.out.println("order.calculatePrice = " + order.calculatePrice());

    }
}
