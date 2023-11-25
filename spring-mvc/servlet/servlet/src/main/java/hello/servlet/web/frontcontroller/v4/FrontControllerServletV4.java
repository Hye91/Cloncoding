package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*")
///front-controller/v3/* v3밑에 하위에 오는 애들을 모두 호출이 되게 한다.
public class FrontControllerServletV4 extends HttpServlet {

    //Mapping 정보 저장하기 : key를 url로 잡고, 그 url에 따른 controller 호출
    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() { //빈 생성자에 매핑정보 담아서 실행이 되면 담아지도록 함
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service"); //서비스로 구현되는 코드

        String requestURI = request.getRequestURI();//url 주소를 받을수 있게함.(우리가 맵핑한 주소와 같을것)
        ControllerV4 controller = controllerMap.get(requestURI); //map을 돌면서 각각의 controller이 호출되게 한다.
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paraMap을 이번에는 넘겨줘야한다
        //전체 파라미터 조회하듯이 (서블릿에서) map의 정보를 다 넘겨야함
        Map<String, String> paramMap = createParamMap(request);
        //V4에서는 model도 만들어서 넘겨줘야한다.
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);//url 주소를 잘 호출 했으면 httpservlet호출하게한다.
        //논리 뷰 이름을 반환한다.
        //ctlr + alt + B : 이 메서드의 구현체로 들어갈수 있다.

//        String viewName = mv.getViewName();//논리이름
        MyView view = viewResolver(viewName);//물리이름으로 변환
        view.render(model , request, response);
    }

    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
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
