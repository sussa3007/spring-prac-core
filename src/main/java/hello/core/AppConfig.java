package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * DIP 위반
 * OrderServiceImpl 는 DiscountPolicy 인터페이스 뿐만아니라
 * RateDiscountPolicy 도 의존 하고 있다.
 * OCP 위반
 * RateDiscountPolicy 구현함으로서 기능을 확장하였다.
 * 기능 확장함과 동시에 "OrderServiceImpl 클래스의 코드를 변경해야한다."
 *     private final DiscountPolicy discountPolicy
 *             = new FixDiscountPolicyImpl();
 *
 * 위 사항들을 개선하기 위해 AppConfig 생성하여 관심사 분리
 * 생성자 주입으로 전환하여 Service 클래스 들은 인터페이스에만 의존하게 된다.
 */

@Configuration
public class AppConfig {
    @Bean
    public MemberService memberService() {
        System.out.println("Call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
        System.out.println("Call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService() {
        System.out.println("Call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

}
