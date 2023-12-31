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

    private Long id;

    @NotBlank(message = "공백X")
    // 문자열 값이 null이 아니며, 최소한 하나의 공백이 아닌 문자를 포함하고 있는지를 확인합니다.
    private String itemName;

    @NotNull
    @Range(min = 1000, max = 1000000)
    private Integer price;

    @NotNull
    @Max(9999)
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
