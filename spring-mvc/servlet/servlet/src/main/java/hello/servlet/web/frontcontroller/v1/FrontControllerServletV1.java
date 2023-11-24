package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
///front-controller/v1/* v1밑에 하위에 오는 애들을 모두 호출이 되게 한다.
public class FrontControllerServletV1 extends HttpServlet {

    //Mapping 정보 저장하기 : key를 url로 잡고, 그 url에 따른 controller 호출
    Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() { //빈 생성자에 매핑정보 담아서 실행이 되면 담아지도록 함
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service"); //서비스로 구현되는 코드

        String requestURI = request.getRequestURI();//url 주소를 받을수 있게함.(우리가 맵핑한 주소와 같을것)
        ControllerV1 controller = controllerMap.get(requestURI); //map을 돌면서 각각의 controller이 호출되게 한다.
        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(request,response); //url 주소를 잘 호출 했으면 httpservlet호출하게한다.
    }
}
