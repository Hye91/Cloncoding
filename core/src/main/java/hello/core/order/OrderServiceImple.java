package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImple implements OrderService{

    //서비스 임플리먼츠는 회원 레포지토리와 할인 정책에 관한게 필요하다
    MemberRepository memberRepository = new MemoryMemberRepository();
    DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //discountPolicy에서는 할인에 대해서 생각해야될 필요가 없이 member만 넘겨주면 되게된다. ->단일책임의 원칙 잘 지켜진것.

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
