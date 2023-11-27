package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof  ControllerV4);
        //핸들러가 컨트롤러 v4버전 체크하는지 확인
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;//handler을 v4버전으로 캐스팅
        Map<String, String> paramMap = createParamMap(request);
        HashMap<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap, model);
        //여기까지하면 handle을 MV를 반환해야하는데 이 경우는 String을 반환해서 맞지 않게 된다
        // -> 이때 adapter의 역할이 등장 modelView를 어댑터에서 생성해준다
        ModelView mv = new ModelView(viewName);
        mv.setModel(model); //view도 model도 셋팅 controller에서 model에 필요한 데이터를 다 담아놓기 때문에 그걸 이용함
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
