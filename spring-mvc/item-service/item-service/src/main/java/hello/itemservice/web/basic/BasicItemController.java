package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    //@Autowired //스프링에 등록된 컴포넌트끼리 관계주입(생성자 관계주입), 생성자가 1개인 경우 생략가능
//    public BasicItemController(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    } @Requirde~ 를 쓰면 final이 붙은 인스턴스의 생성자를 만들어준다

    @GetMapping //아이템 목록 출력
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}") //제품상세 출력
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add") //상품등록 '폼'을 보여주기라서 post아니고 get을 사용
    public String addForm(){
        return "basic/addForm";
    }

    //@PostMapping("/add") //상품등록
    public String addItemV1(
            @RequestParam String itemName,
            @RequestParam int price,
            @RequestParam Integer quantity,
            Model model
    ){

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);

        model.addAttribute("item", item);
        //저장하고 나서 제품상세 화면은 바로 보여준다. 그래서 바로 modelAttribute로 넘겨주는것
        return "basic/item";
    }

    //@PostMapping("/add") //상품등록
    public String addItemV2(@ModelAttribute("item") Item item, Model model){

//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);
        itemRepository.save(item);

        //model.addAttribute("item", item);
        //@ModelAttribute("item")이걸 사용해서 위 코드를 주석 처리가능하게한다 (name을 지정해준것, view에서 이 name을 활용)

        //modelAttribute는 model객체에 값을 다 넣어주고 view에도 넣어주는 작업을 한다
        //@RequesetParam으로 하나하나 객체 불러서 값을 담아준 작업도 같이 해주고
        //model에도 담아주게되는것

        //저장하고 나서 제품상세 화면은 바로 보여준다. 그래서 바로 modelAttribute로 넘겨주는것
        return "basic/item";
    }

    //@PostMapping("/add") //상품등록
    public String addItemV3(@ModelAttribute Item item){
        //만약 name을 지운 경우
        //클래스명 Item의 첫글자인 대문자를 Item -> item으로 바꾼것을 modelAttribute에서 name으로 사용한다
        itemRepository.save(item);

        return "basic/item";
    }

    @PostMapping("/add") //상품등록
    public String addItemV4(Item item){
        //여기서도 클래스명 Item의 첫글자인 대문자를 Item -> item으로 바꾼것을 modelAttribute에서 name으로 사용한다
        itemRepository.save(item);

        return "basic/item";
    }

    @GetMapping("/{itemId}/edit") //수정하는 폼 보여주기
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item",item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit") //수정하는 폼 보여주기
    public String edit(@PathVariable Long itemId,@ModelAttribute Item item){

        itemRepository.update(itemId,item);
        return "redirect:/basic/items/{itemId}"; //redirect사용
        //return "basic/item";
    }

    /***
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA",10000,10));
        itemRepository.save(new Item("itemB",20000,20));
        itemRepository.save(new Item("itemC",30000,30));
    }
}
