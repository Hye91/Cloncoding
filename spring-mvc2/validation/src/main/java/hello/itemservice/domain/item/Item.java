package hello.itemservice.domain.item;

import lombok.Data;
import org.hibernate.validator.constraints.Range; //hibernate의 validator에서만 동작하게 된다

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
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
