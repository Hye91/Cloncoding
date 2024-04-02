package hello.itemservice.domain;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import hello.itemservice.repository.memory.MemoryItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional //테스트에서 Transactional Annotation사용사면 트랜잭션 시작과 롤백을 관리할수있다
@SpringBootTest //이 어노테이션이붙어있으면 @SpringBootApplication 어노테이션을 찾아내서 설정으로 사용하게 된다.
    //그래서 @SpringBootApplication에 설정되어있는 import설정으로 테스트하게된다
class ItemRepositoryTest {

    /**
     * 테스트에서 중요한건 격리성! 테스트를 실행할때 기존에 저장되어있는 값이 방해가 되면 안된다
     * 데이터가 깔끔해야 테스트를 방해하지 않고 원활한 진행이 되게 된다.
     */
    @Autowired
    ItemRepository itemRepository;

    /*@Autowired
    PlatformTransactionManager transactionManager;
    //dataSource랑 transactionManager는 Spring이 자동으로 bean 등록해준다

    TransactionStatus status;

    @BeforeEach
    void beforeEach(){
        //트랜잭션 시작
        status = transactionManager.getTransaction(new DefaultTransactionDefinition());
        //테스트 시작하기 전에 Transaction을 시작하게 된다. (롤백으로 테스트를 처음의 상태로 돌리기 위함)
    }*/

    @AfterEach
    void afterEach() {
        //MemoryItemRepository 의 경우 제한적으로 사용
        if (itemRepository instanceof MemoryItemRepository) {
            ((MemoryItemRepository) itemRepository).clearStore();
        }
        //Transaction RollBack
//        transactionManager.rollback(status);
    }

    @Test
    @Transactional
    @Commit //테스트의 결과를 저장해서 확인하고 싶을때는 commit을 해줄수 있다. rollbach(false)를 사용할수도있다.
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId()).get();
        assertThat(findItem).isEqualTo(savedItem);
    }

    @Test
    void updateItem() {
        //given
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        ItemUpdateDto updateParam = new ItemUpdateDto("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId).get();
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

    @Test
    void findItems() {
        //given
        Item item1 = new Item("itemA-1", 10000, 10);
        Item item2 = new Item("itemA-2", 20000, 20);
        Item item3 = new Item("itemB-1", 30000, 30);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        //둘 다 없음 검증
        test(null, null, item1, item2, item3);
        //이름과 가격값이 null인 경우에는 item1~3까지 3개의 데이터가 있어야하는데 JdbcTemplate를 사용해서
        //테스트를 하게되면 이 세값 이외에도 많은 값들이 기존에 저장되어 있기때문에 오류가 생기게된다.
        test("", null, item1, item2, item3);

        //itemName 검증
        test("itemA", null, item1, item2);
        test("temA", null, item1, item2);
        test("itemB", null, item3);

        //maxPrice 검증
        test(null, 10000, item1);

        //둘 다 있음 검증
        test("itemA", 10000, item1);
    }

    void test(String itemName, Integer maxPrice, Item... items) {
        //검색의 조건을 입력하고, 이후에는 입련되어있는 아이템 항목을 나열하게된다.
        List<Item> result = itemRepository.findAll(new ItemSearchCond(itemName, maxPrice));
        assertThat(result).containsExactly(items); //Exactly를 하면 순서도 다 맞아야한다.
    }
}
