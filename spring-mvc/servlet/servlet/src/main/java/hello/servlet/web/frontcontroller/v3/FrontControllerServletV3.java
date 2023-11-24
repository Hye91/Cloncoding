package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
///front-controller/v3/* v3밑에 하위에 오는 애들을 모두 호출이 되게 한다.
public class FrontControllerServletV3 extends HttpServlet {

    //Mapping 정보 저장하기 : key를 url로 잡고, 그 url에 따른 controller 호출
    Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() { //빈 생성자에 매핑정보 담아서 실행이 되면 담아지도록 함
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service"); //서비스로 구현되는 코드

        String requestURI = request.getRequestURI();//url 주소를 받을수 있게함.(우리가 맵핑한 주소와 같을것)
        ControllerV3 controller = controllerMap.get(requestURI); //map을 돌면서 각각의 controller이 호출되게 한다.
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paraMap을 이번에는 넘겨줘야한다
        //전체 파라미터 조회하듯이 (서블릿에서) map의 정보를 다 넘겨야함
        Map<String, String> paramMap = createParamMap(request);

        //메서드 추출 : ctrl + alt + m

        ModelView mv = controller.process(paramMap);//url 주소를 잘 호출 했으면 httpservlet호출하게한다.
        //ctlr + alt + B : 이 메서드의 구현체로 들어갈수 있다.

        //mv(modelView까지 받았으면 이제 model의 논리 이름을 물리적 이름으로 바꿔야한다.)
        String viewName = mv.getViewName();//논리이름
        MyView view = viewResolver(viewName);//물리이름으로 변환
        view.render(mv.getModel(), request, response);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
