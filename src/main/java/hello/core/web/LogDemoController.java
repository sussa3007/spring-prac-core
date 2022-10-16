package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    /* 서버가 실행될때 request scope 빈은 생성될수가 없다
    *  Provider를 활용해서 해결 해야한다
    *  하지만 MyLogger에서 proxyMode를 사용하면 Provider를 사용할 필요도 없어진다*/
//    private final ObjectProvider<MyLogger> myLoggerProvider;
        private final MyLogger myLogger;

    @RequestMapping("/log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject();
        /* 주입해주는 가짜 프록시 클래스를 확인해보자 */
        System.out.println("myLogger = " + myLogger.getClass());

        myLogger.setRequestURL(requestURL);
        myLogger.log("Controller Test");
        logDemoService.logic("testId");
        return "OK";
    }
}
