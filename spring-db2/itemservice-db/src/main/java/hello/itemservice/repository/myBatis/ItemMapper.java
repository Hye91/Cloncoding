package hello.itemservice.repository.myBatis;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void save(Item item);

    void update(@Param("id") Long id, @Param("updateParam")ItemUpdateDto updateParam);
    //파라미터가 2개가 넘어가는 경우에는 @Param을 사용해야한다

    Optional<Item> findById(Long id);
    //Optional로 처리하는 이유, Optional을 사용하면 null값에 대한 안전한 반환을 명시해주기때문에 사용
    List<Item> findAll(ItemSearchCond itemSearch);
}
