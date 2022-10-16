package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {
        /* 주입할 대상이 없으면 수정자 자체가 호출 되지 않는다. */
        @Autowired(required = false)
        public void setNoBean1(Member member1) {
            System.out.println("member1 = " + member1);
        }
        /* 호출은 되지만 Null로 주입된다.*/
        @Autowired
        public void setNoBean2(@Nullable Member member2) {
            System.out.println("member2 = " + member2);
        }
        /* null 말고 Optional.empty 객체로 주입된다 */
        @Autowired
        public void setNoBean3( Optional<Member> member3) {
            System.out.println("member3 = " + member3);
        }
    }

}
