package hello.core.lifeCycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    void test() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();

    }
    @Configuration
    static class LifeCycleConfig {
        /* PostConstructure, PreDestroy를 사용하면 된다*/
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }

//    @Configuration
//    static class LifeCycleConfig {
//        /* destroyMethod의 기본값은 close,shotdown 같은 종료 메소드를 자동으로 호출한다
//        *  close 메소드가 존재한다면 destroyMethod의 값을 설정하지 않아도 자동 호출된다.
//        * 외부라이브러리에 사용하면 된다 */
//        @Bean(initMethod = "init", destroyMethod = "close")
////        @Bean
//        public NetworkClient networkClient() {
//            NetworkClient networkClient = new NetworkClient();
//            networkClient.setUrl("http://hello-spring.dev");
//            return networkClient;
//        }
//    }


}
