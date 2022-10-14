package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicyImpl;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;


public class OrderServiceImpl implements OrderService {
    private final MemberRepository memberRepository
            = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy
            = new RateDiscountPolicy();
    /**
     * DIP 위반
     * OrderServiceImpl 는 DiscountPolicy 인터페이스 뿐만아니라
     * RateDiscountPolicy 도 의존 하고 있다.
     * OCP 위반
     * RateDiscountPolicy 구현함으로서 기능을 확장하였다.
     * 기능 확장함과 동시에 "OrderServiceImpl 클래스의 코드를 변경해야한다."
     *     private final DiscountPolicy discountPolicy
     *             = new FixDiscountPolicyImpl();
     */


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
