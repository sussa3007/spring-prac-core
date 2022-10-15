package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac
                = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA A 사용자 10000주문
        statefulService1.order("userA",10000);
        // ThreadA B 사용자 20000주문
        statefulService2.order("userB",20000);
        //A의 주문 금액 조회
//        int price = statefulService1.getPrice();
        int price = statefulService1.order("userA",10000);
        System.out.println("price = " + price);
        // 20000 이 출력된다
        // 같은 객체기 때문에 10000원으로 할당되었던 price의 값이 20000으로 바뀜
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
        Assertions.assertThat(price).isEqualTo(10000);


    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}