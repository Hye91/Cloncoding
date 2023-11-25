package hello.servlet.web.frontcontroller.v4;

import java.util.Map;

public interface ControllerV4 {

    /**
     *
     * @param paramMap
     * @param model
     * @return viewName
     */
    String process(Map<String,String> paramMap, Map<String, Object> model);
                                                //key는 String 고 Object에는 아무거나 다 넣을수 있다는듯
}
