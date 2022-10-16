package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class OrderServiceTest {
    OrderService orderService;
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void orderTest() {
        // Given
        long memberId = 1L;
        Member member = new Member(memberId, "mameberA", Grade.VIP);
        memberService.join(member);
        Order order = orderService
                .createOrder(memberId, "itemA", 10000);
        // When
        // Then
        Assertions
                .assertThat(order.getDiscountPrice()).isEqualTo(1000);
        Assertions
                .assertThat(order.calculatePrice()).isEqualTo(9000);
    }

}