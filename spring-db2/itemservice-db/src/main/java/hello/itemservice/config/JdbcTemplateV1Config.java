package hello.itemservice.config;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.jdbctemplate.JdbcTemplateItemRepositoryV1;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration //설정
@RequiredArgsConstructor
public class JdbcTemplateV1Config {
    private final DataSource dataSource;

    //지금 각각 service와 repository를 인터페이스로 만들어뒀기때문에
    //각각의 구현체를 configuration을 통해 지정해줘야한다. 이걸 통해서 version을 관리하고
    //DB의 구현도 관리를 할수 있게된다.

    @Bean
    public ItemService itemService(){
        return new ItemServiceV1(itemRepository());
    }

    @Bean
    public ItemRepository itemRepository(){
        return new JdbcTemplateItemRepositoryV1(dataSource);
    }
}
