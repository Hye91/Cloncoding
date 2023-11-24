package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
///front-controller/v1/* v1밑에 하위에 오는 애들을 모두 호출이 되게 한다.
public class FrontControllerServletV2 extends HttpServlet {

    //Mapping 정보 저장하기 : key를 url로 잡고, 그 url에 따른 controller 호출
    Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() { //빈 생성자에 매핑정보 담아서 실행이 되면 담아지도록 함
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service"); //서비스로 구현되는 코드

        String requestURI = request.getRequestURI();//url 주소를 받을수 있게함.(우리가 맵핑한 주소와 같을것)
        ControllerV2 controller = controllerMap.get(requestURI); //map을 돌면서 각각의 controller이 호출되게 한다.
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(request, response);//url 주소를 잘 호출 했으면 httpservlet호출하게한다.
        //ctlr + alt + B : 이 메서드의 구현체로 들어갈수 있다.
        view.render(request,response);
    }
}
