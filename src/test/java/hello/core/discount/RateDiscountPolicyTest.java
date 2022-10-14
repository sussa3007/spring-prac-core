package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용 되어야한다.")
    void vip_o() {
        // Given
        Member member = new Member(1L, "memberVIP", Grade.VIP);
        // When
        int discount = discountPolicy.discount(member, 10000);
        // Then
        Assertions.assertThat(discount).isEqualTo(1000);
    }
    @Test
    @DisplayName("VIP가 아닐때 10% 할인이 적용 되지 않아야한다.")
    void vip_x() {
        // Given
        Member member = new Member(1L, "memberBASIC", Grade.BASIC);
        // When
        int discount = discountPolicy.discount(member, 10000);
        // Then
        Assertions.assertThat(discount).isEqualTo(0);
    }


}