package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String,Object> handlerMappingMap = new HashMap<>();
    //private Map<String, ControllerV4> controllerMap = new HashMap<>();
    //이제까지 v4까지는 map에 value값으로 정해진 컨트롤러를 담아야했지만
    //adapter방식으로는 새로 만드는 것들이 다 들어갈수 있어야한다.
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();
    //handlerAdapter List중에서 찾아와야하므로 List를 만들어준다.


    public FrontControllerServletV5() { //생성자에 맵핑 정보, 어댑터 정보 넣어서 초기화
        initHandlerMappingMap();

        initHandlerAdapter();
    }

    private void initHandlerAdapter() {
        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
        //v4 추가
        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //핸들러 조회
        Object handler = getHandler(request);

        if(handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //핸들러 어댑터 찾아오기
        MyHandlerAdapter adapter = getHandlerAdapter(handler);

        //핸들 호출해서 모델 뷰 찾아오기, 논리 뷰 이름을 반환
        ModelView mv = adapter.handle(request, response, handler);

        //메서드 추출 : ctrl + alt + m

        //ctlr + alt + v : 이 메서드의 구현체로 들어갈수 있다.

        //mv(modelView까지 받았으면 이제 model의 논리 이름을 물리적 이름으로 바꿔야한다.)
        String viewName = mv.getViewName();//논리이름
        MyView view = viewResolver(viewName);//물리이름으로 변환
        view.render(mv.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) { //컬렉션.iter하면 for문으로 바꿔준다.
            if(adapter.supports(handler)){
                return adapter;
            }
        }

        throw new IllegalArgumentException("handler Adapter를 찾을 수 없습니다. handler = " + handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();//url 주소를 받을수 있게함.(우리가 맵핑한 주소와 같을것)
        return handlerMappingMap.get(requestURI); //map을 돌면서 각각의 controller이 호출되게 한다.
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
