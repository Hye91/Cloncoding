package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImple implements OrderService{

    //서비스 임플리먼츠는 회원 레포지토리와 할인 정책에 관한게 필요하다
    private final MemberRepository memberRepository; // = new MemoryMemberRepository();
    //OrderServiceImple에서 구현체를 MemoryMemberRepository사용하도록 지정해주고 있다.
    //AppConfig를 통해서 구현을 지정하도록 설정을 바꿔주어야한다.

    //DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private DiscountPolicy discountPolicy;
    //FixDiscountPolicy, RateDiscountPolicy에 의존하지 않는 관계를 만들어줘야 해서 변경
    //이렇게 되면 OrderServiceImple은 인터페이스인 DiscountPolicy만을 의존하게 된다
    //Test를 하면 nullPointException이 터진다. 구현체를 할당하지 않았기때문에 null이 되므로!
    //누군가가 DiscountPolicy를 대신 생성해서 주입을 해줘야한다!


    public OrderServiceImple(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    } //생성자 외부 주입, AppConfig에서 의해서 관리되게 하기 위해서 생성자 외부주입으로 바꾼다.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //discountPolicy에서는 할인에 대해서 생각해야될 필요가 없이 member만 넘겨주면 되게된다. ->단일책임의 원칙 잘 지켜진것.

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
