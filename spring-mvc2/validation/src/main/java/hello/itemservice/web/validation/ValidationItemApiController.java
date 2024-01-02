package hello.itemservice.web.validation;

import hello.itemservice.web.validation.form.ItemSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/validation/api/items")
public class ValidationItemApiController {

    @PostMapping("/add")
    public Object addItem(@RequestBody @Validated ItemSaveForm form, BindingResult bindingResult){

        //json 타입으로 할 경우 일단 객체로 변환을 하고 그 한 덩어리를 오류가 있을시 넘겨줘야하는데
        //타입을 맞게 넣지 않았을 경우에는 객체 변환이되지를 않는다 -> Controller자체가 호출이 안된다
        //그래서 log를 확인하려고 한 log도 보이지 않게된다.

        //타입은 다 맞게 넣었는데 범위가 벗어나는 경우에는 객체의 생성되고, 컨트롤러 호출까지 뜬다.
        //그 이후에 오류가 발생하게 되는것이다

        log.info("API 컨트롤러 호출");

        if(bindingResult.hasErrors()){
            log.info("오류 발생 errors ={}", bindingResult);
            return bindingResult.getAllErrors(); //field, object Error 모두 보여주게 된다
        }

        log.info("성공 로직 호출");
        return form;
    }
}
