package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
        //ControllerV3의 인스턴스인 경우에만 true를 반환하게 된다.
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;

        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        return mv;
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator() /*getParameterNames : 모든 queryNames들 조회*/
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        //각 파라미터의 이름을 나타내는 paramName을 받아서 paramMap에 해당 파라미터의 이름을 키로하고,
        // 그에 해당하는 값을 request.getParameter(paramName)으로 가져와서 값으로 설정하는 작업
        return paramMap;
    }

}
