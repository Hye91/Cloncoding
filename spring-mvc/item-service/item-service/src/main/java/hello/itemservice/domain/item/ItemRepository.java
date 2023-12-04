package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //실제로는 hashMap 사용하면 안된다,
    //멀티스레드로 여러개가 동시에 store에 접근 할 경우 hasgMap을 사용하면 안된다.
    // static 사용한거 주의하기 (스프링에서 싱글톤으로 생성되겠지만 new를 하게되면 또 생성될수 있어서 조심하는건가?)
    private static long sequence = 0L; //아이디생성
    //동시에 접근하는 (동시성)것을 생각해야하는 경우 hashMap 말고 cuncurrentMap, long 말로 atomic long등을 사용해야한다.
    public Item save(Item item){
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id){
        return store.get(id);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam){
        //id를 넣고 업데이트할 parameter을 넣으면 업데이트 되도록함
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        //사실 parameterItemDto를 만들어서 수정할 파라미터만 담긴 객체를 하나 더 만들어주는게 명확해서 좋다
        //이렇게 하면 id또한 수정을 해야하는지 아닌지에 대해서 모호해지기 때문이다.
    }

    public void clearStore(){ //테스트용
        store.clear();
    }
}
