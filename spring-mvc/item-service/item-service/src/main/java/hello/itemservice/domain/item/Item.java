package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data 핵심 도메인에 사용하기에는 위험하다, dto의 경우에는 사용해도 가능
@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price; //가격이랑 수량이 null인경우 대비해서 Integer로 설정
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
