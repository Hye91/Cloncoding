package hello.core.order;

public interface OrderService {
    //return으로 주문결과 반환하는 부분
    Order createOrder(Long memberId, String itemName, int itemPrice); //반환 타입 + 메서드()
}
