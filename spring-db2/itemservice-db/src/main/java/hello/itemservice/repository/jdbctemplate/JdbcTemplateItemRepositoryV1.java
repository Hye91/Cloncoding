package hello.itemservice.repository.jdbctemplate;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JdbcTemplate 사용
 */
@Slf4j
public class JdbcTemplateItemRepositoryV1 implements ItemRepository {

    private final JdbcTemplate template;

    public JdbcTemplateItemRepositoryV1(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Item save(Item item) {
        String sql = "insert into item(item_name, price,quantity) values (?,?,?)";
        //db에서 생성해준 id값을 select로 사용하는 방법(메모리에서 사용한것처럼 자동으로 증가하는 id를 만들어야한다)
        KeyHolder keyHolder = new GeneratedKeyHolder(); //jdbc에서 id값을 가져올때 이런 방식을 사용하는구나 정도로 이해
        template.update(connection -> {
            //자동 증가 키
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1,item.getItemName());
            ps.setInt(2,item.getPrice());
            ps.setInt(3,item.getQuantity());
            return ps;
        },keyHolder);

        long key = keyHolder.getKey().longValue();
        item.setId(key);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        String sql = "update item set item_name=?, price=?, quantity=? where id = ?";
        template.update(sql,
                updateParam.getItemName(),
                updateParam.getPrice(),
                updateParam.getPrice(),
                itemId);
    }

    @Override
    public Optional<Item> findById(Long id) {
        String sql = "select id, item_name, price, quantity from item where id = ?";
        //ResultSet은 쿼리의 실행결과값을 담아두는 인터페이스이다
        //그래서 다시 id를 통해서 값을 객체로 가져오기위해서는 rowMapper을 이용해서
        //객체로 변환해서 가져와야한다!
        try{
            Item item = template.queryForObject(sql, itemRowMapper(), id);
            //queryForObject(결과값 하나만 가져오는 경우)의 경우에는 결과값이 없으면 예외가 터진다
            return Optional.of(item); //옵셔널로 반환되기 때문에 of 조건 걸어서 , 단 of의 경우 null이면 안된다
        }catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond/*검색조건사항*/) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        String sql = "select id, item_name, price, quantity from item";
        //검색조건에 따른 동적쿼리 작성
        //동적쿼리 : 검색의 조건에 따른 각각의 where 이후의 조건이 변경되어야한다
        //이름만 검색하는경우, 가격만 검색하는경우, 이름과 가격모두 검색하는경우 (and조건 추가)
        //이런 모든 경우의 수를 생각해야하므로 동적으로 적용시키는 동적쿼리가 필요하게 된다
        if (StringUtils.hasText(itemName) || maxPrice != null) {
            sql += " where";
        }
        boolean andFlag = false; //하나라도 조건이 더 붙으면 and를 붙이는 것
        List<Object> param = new ArrayList<>();
        if (StringUtils.hasText(itemName)) {
            sql += " item_name like concat('%',?,'%')";
            param.add(itemName);
            andFlag = true;
        }
        if (maxPrice != null) {
            if (andFlag) {
                sql += " and";
            }
            sql += " price <= ?";
            param.add(maxPrice);
        }
        log.info("sql={}", sql);
        return template.query/*여러개의 list 결과값 가져올때*/(sql, itemRowMapper(), param.toArray());
    }

    private RowMapper<Item> itemRowMapper() {
        return ((rs, rowNum) -> {
            //새로운 객체 생성, item 객체에 쿼리의 실행결과값을 저장해서 반환해주기
            Item item = new Item();
            item.setId(rs.getLong("id"));
            item.setItemName(rs.getString("item_name"));
            item.setPrice(rs.getInt("price"));
            item.setQuantity(rs.getInt("quantity"));
            return item;
        });
    }
}
