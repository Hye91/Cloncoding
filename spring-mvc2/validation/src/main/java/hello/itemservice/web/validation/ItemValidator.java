package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ItemValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);
        //파라미터로 넘어오는 clazz가 Item.class(Item클래스 객체를 말함)에 포함이 되는지를 검증한다.(자식 클래스까지 포함한다)
        //해당 검증기를 지원하는지 여부를 알려준다.
    }

    @Override //실제 검증로직
    public void validate(Object target, Errors errors) {
        Item item = (Item) target; //캐스팅, 형변환을 하는것. 스프링 자체에서 Object로 고정이 되어 있기때문에 우리가 캐스팅을 해야한다.
        //errors에 bindingResult를 넣어줄수 있게 된다 -> error의 자식클래스가 bindingResult

        //ValidationUtils.rejectIfEmptyOrWhitespace(errors,"itemName","required");

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            errors.rejectValue("itemName","required");
        }


        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
             errors.rejectValue("price","range",new Object[]{1000,1000000}, null);
        }
        if(item.getQuantity() == null || item.getQuantity() >= 9999){

            errors.rejectValue("quantity","max", new Object[]{9999}, null); //rejectValue는 fieldError
        }

        //특정 필드가 아닌 복합 룰 검증 (globalError)
        //objectError은 값이 남아있는게 없기때문에 rejectedValue나 bindingFailure 추가하지않는다
        if(item.getQuantity() != null && item.getPrice() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                errors.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
                //reject는 Object , Object 이름은 bindingResult가 이미 알고 있기때문에 여기서 끝
            }
        }
    }
}
