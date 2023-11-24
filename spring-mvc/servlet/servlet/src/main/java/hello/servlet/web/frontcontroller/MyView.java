package hello.servlet.web.frontcontroller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyView {

    //매번 jsp를 호출하는것이아니고 myview를 통해서 view에 관한것을 모두 관리
    //forntController에서도 myView를 호출해서 관리하게 한다.

    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    } //실행이 되면 생성자를 통해서 viewPath를 가져올수 있도록함.

    //jsp로 이동하는것 -> 렌더링한다고 표현(view를 만드는행위자체)
    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request,response);
    }
}
