package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import 코드만 보고 의존관계를 쉽게 판단할수 있는관계 = 정적인 의존관계
@Component
public class OrderServiceImple implements OrderService{

    //서비스 임플리먼츠는 회원 레포지토리와 할인 정책에 관한게 필요하다
    /*@Autowired 직접주입방법*/ private final MemberRepository memberRepository; // = new MemoryMemberRepository();
    //OrderServiceImple에서 구현체를 MemoryMemberRepository사용하도록 지정해주고 있다.
    //AppConfig를 통해서 구현을 지정하도록 설정을 바꿔주어야한다.

    //DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //AppConfig 생성 이전에는 이 부분의 수정이 필요했다 그러나 지금은 필요하지 않게된다.

    /*@Autowired*/ private final DiscountPolicy discountPolicy;
    //FixDiscountPolicy, RateDiscountPolicy에 의존하지 않는 관계를 만들어줘야 해서 변경
    //이렇게 되면 OrderServiceImple은 인터페이스인 DiscountPolicy만을 의존하게 된다
    //Test를 하면 nullPointException이 터진다. 구현체를 할당하지 않았기때문에 null이 되므로!
    //누군가가 DiscountPolicy를 대신 생성해서 주입을 해줘야한다!

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) { //수정자 주입
//        System.out.println("memberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }
//
//    @Autowired(required = false)//수정자 주입의 경우 스프링 빈이 등록 된 다음 단계인 의존관계 주입에서 일어나게 된다.
//    //required = false 로 해두면 선택적으로 주입을 만들수 있다.
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("discountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }

    @Autowired //자동으로 의존관계가 주입되도록 설정 / 생성자주입
    public OrderServiceImple(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        //생성자 주입의 경우에는 빈을 등록하면서 의존관계 주입도 같이 일어나게 된다.
        //스프링이 실행되면 생성자의 호출이 바로 일어나기 때문에
        System.out.println("1. OrderServiceImple.OrderServiceImple"); //제일먼저 호출되는 순서임을 알수있다.
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    } //생성자 외부 주입, AppConfig에서 의해서 관리되게 하기 위해서 생성자 외부주입으로 바꾼다.
    // 문서에 null이 허용가능한 경우를 제외하고는 대부분 값을 다 채워넣어야한다고 생각하면 된다.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        //discountPolicy에서는 할인에 대해서 생각해야될 필요가 없이 member만 넘겨주면 되게된다. ->단일책임의 원칙 잘 지켜진것.

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
    //singleton이 깨지는지 아닌지 테스트 용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
