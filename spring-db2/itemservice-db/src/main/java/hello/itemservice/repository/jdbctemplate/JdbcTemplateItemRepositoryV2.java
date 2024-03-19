package hello.itemservice.repository.jdbctemplate;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * NamedParameterJdbcTemplate 사용
 * 파라미터의 순서에 맞게 저장하지 않으면 DB를 다 고쳐야하는 경우가
 * 생기는 것을 막기위해서 이름으로 파라미터를 지정하는게 생김
 * 파라미터 바인딩을 순서가 아닌 이름 기반으로 한다.
 *
 *SqlParameterSource
 * - BeanPropertySqlParameterSource
 * - MapSqlParameterSource
 *Map
 *
 *BeanPropertyRowMapper
 */
@Slf4j
public class JdbcTemplateItemRepositoryV2 implements ItemRepository {

//    private final JdbcTemplate template;
    private final NamedParameterJdbcTemplate template;
    public JdbcTemplateItemRepositoryV2(DataSource dataSource) {

        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Item save(Item item) {
        String sql = "insert into item(item_name, price,quantity) "+
                "values (:itemName,:price, :quantity)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        //순서가 아닌 이름으로 파라미터를 넘겨줘야한다. 이름으로 파라미터 넘기는 방법 1
        //저장하기 위해서 넘겨준 값들을 이용해서 parameter을 만들어준다.
        SqlParameterSource param = new BeanPropertySqlParameterSource(item);

        template.update(sql,param,keyHolder);
//        template.update(connection -> {
//            //자동 증가 키
//            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
//            ps.setString(1,item.getItemName());
//            ps.setInt(2,item.getPrice());
//            ps.setInt(3,item.getQuantity());
//            return ps;
//        },keyHolder);
        long key = keyHolder.getKey().longValue();
        item.setId(key);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        String sql = "update item set item_name=:itemName, price=:price, quantity=:quantity where id =:id";

        //이름 파라미터로 넘기는 방법 2
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("itemName", updateParam.getItemName())
                .addValue("price", updateParam.getPrice())
                .addValue("quantity", updateParam.getQuantity())
                .addValue("id",itemId);

        template.update(sql,param);

//        template.update(sql,
//                updateParam.getItemName(),
//                updateParam.getPrice(),
//                updateParam.getPrice(),
//                itemId);
    }

    @Override
    public Optional<Item> findById(Long id) {
        String sql = "select id, item_name, price, quantity from item where id = :id";
        //ResultSet은 쿼리의 실행결과값을 담아두는 인터페이스이다
        //그래서 다시 id를 통해서 값을 객체로 가져오기위해서는 rowMapper을 이용해서
        //객체로 변환해서 가져와야한다!
        try{
            //이름 파라미터로 넘기는 방법 3
            Map<String, Object> param = Map.of("id", id);
            //param = Map.of("id", id): 이 부분은 새로운 맵을 생성하여 변수 param에 할당하는 부분입니다.
            // Map.of() 메서드는 Java 9에서 추가된 메서드로, 지정된 키와 값으로 이루어진 불변(immutable) 맵을 생성합니다.
            // 여기서 "id"는 맵의 키이고, id는 해당 키에 대응하는 값입니다.
            Item item = template.queryForObject(sql,param, itemRowMapper());
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

        //이름 파라미터로 넘기는 방법 4
        //cond의 이름을 가지고 parameter값으로 넘겨주는것
        SqlParameterSource param = new BeanPropertySqlParameterSource(cond);

        String sql = "select id, item_name, price, quantity from item";
        //검색조건에 따른 동적쿼리 작성
        //동적쿼리 : 검색의 조건에 따른 각각의 where 이후의 조건이 변경되어야한다
        //이름만 검색하는경우, 가격만 검색하는경우, 이름과 가격모두 검색하는경우 (and조건 추가)
        //이런 모든 경우의 수를 생각해야하므로 동적으로 적용시키는 동적쿼리가 필요하게 된다
        if (StringUtils.hasText(itemName) || maxPrice != null) {
            sql += " where";
        }
        boolean andFlag = false; //하나라도 조건이 더 붙으면 and를 붙이는 것
//        List<Object> param = new ArrayList<>();
        if (StringUtils.hasText(itemName)) {
            sql += " item_name like concat('%',:itemName,'%')";
//            param.add(itemName);
            andFlag = true;
        }
        if (maxPrice != null) {
            if (andFlag) {
                sql += " and";
            }
            sql += " price <= :maxPrice";
//            param.add(maxPrice);
        }
        log.info("sql={}", sql);
        return template.query/*여러개의 list 결과값 가져올때*/(sql,param, itemRowMapper());
    }

    private RowMapper<Item> itemRowMapper() {
//        return ((rs, rowNum) -> {
//            //새로운 객체 생성, item 객체에 쿼리의 실행결과값을 저장해서 반환해주기
//            Item item = new Item();
//            item.setId(rs.getLong("id"));
//            item.setItemName(rs.getString("item_name"));
//            item.setPrice(rs.getInt("price"));
//            item.setQuantity(rs.getInt("quantity"));
//            return item;
//        }); 이거의 경우에도 item 클래스와 비슷하기때문에 깔끔하게 정리할수 있다
        return BeanPropertyRowMapper.newInstance(Item.class);  //camel 변환 지원
        //데이터베이스의 특정 행(row)을 Java 객체로 매핑(mapping)
    }
}
