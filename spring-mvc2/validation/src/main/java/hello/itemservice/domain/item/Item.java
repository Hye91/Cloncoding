package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range; //hibernate의 validator에서만 동작하게 된다
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
//@ScriptAssert(lang = "javascript", script = "_this.price * _this.quantity >= 10000", message = "총 합이 10000원이 넘게 입력해주세요")
//Object 오류에 대한 해결은 ScriptAssert를 이용하면 전체적으로 해결을 할수 있게된다.
//기능이 너무 약하기때문에 주석처리함 , _this.는 지금 내 객체를 의미함
public class Item {

    //beanValidation의 groups 기능을 사용해서 각 기능별로 그룹을 나누어서 적용시킬수 있다.

    //@NotNull(groups = UpdateCheck.class)
    private Long id;

    //@NotBlank(message = "공백X",groups = {SaveCheck.class, UpdateCheck.class})
    // 문자열 값이 null이 아니며, 최소한 하나의 공백이 아닌 문자를 포함하고 있는지를 확인합니다.
    private String itemName;

    //@NotNull(groups = {SaveCheck.class, UpdateCheck.class})
   //@Range(min = 1000, max = 1000000,groups = {SaveCheck.class, UpdateCheck.class})
    private Integer price;

    //@NotNull(groups = {SaveCheck.class, UpdateCheck.class})
    //@Max(value = 9999 , groups = SaveCheck.class)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
