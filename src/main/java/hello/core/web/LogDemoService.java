package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    /* 서버가 실행될때 request scope 빈은 생성될수가 없다
     *  Provider를 활용해서 해결 해야한다
     *  하지만 MyLogger에서 proxyMode를 사용하면 Provider를 사용할 필요도 없어진다*/
//    private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;


    public void logic(String testId) {
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.log("Service Id = " + testId);
    }
}
