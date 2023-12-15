package hello.itemservice.web.form;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.print.DocFlavor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/form/items")
@RequiredArgsConstructor
public class FormItemController {

    private final ItemRepository itemRepository;
    //"regions"이라는 작업을 3군데에서 중복적으로 진행해야한다.
    //이걸 스프링이 기능을 제공함으로써 중복작업을 줄여준다
    @ModelAttribute("regions") //이건 Model에서 사용하는 modelAttribute와는 다른 기능을한다
    //컨트롤러 호출시 자동으로 addAttribute를 해서 Model에 담기게한다.
    public Map<String, String> regions() {
        Map<String, String> regions = new LinkedHashMap<>(); //그냥 HashMap을 사용하면 순서가 보장이 되지 않기때문에 Linked사용
        //Linked를 사용하면 순서대로 들어가게 된다
        regions.put("SEOUL", "서울"); //키와 벨류로 입력한다
        regions.put("BUSAN", "부산");
        regions.put("JEJU", "제주");
        return regions;
    }

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "form/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

//        Map<String, String> regions = new LinkedHashMap<>();
//        regions.put("SEOUL", "서울"); //key & value로 입력한다
//        regions.put("BUSAN", "부산");
//        regions.put("JEJU", "제주");
//        model.addAttribute("regions",regions);
        //Model에 자동으로 담기게 되는 과정을 거쳤으므로 다 지워도 된다.
        return "form/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        //타임리프가 지원하는 폼 기능을 쓰려면 일단 모델에서 객체를 넘겨줘야한다.(빈 객체라도 넘겨야함)

//        Map<String, String> regions = new LinkedHashMap<>();
////        regions.put("SEOUL", "서울"); //키와 벨류로 입력한다
////        regions.put("BUSAN", "부산");
////        regions.put("JEJU", "제주");
////        model.addAttribute("regions",regions);

        return "form/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item/*타임리프로 폼에 등록을 item을 해두면 이렇게 등록시 사용과 맞춰줄수있다*/,
                          RedirectAttributes redirectAttributes) {
        log.info("item.open={}", item.getOpen());
        log.info("item.regions={}", item.getRegions());

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/form/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);

//        Map<String, String> regions = new LinkedHashMap<>();
//        regions.put("SEOUL", "서울"); //key & value로 입력한다
//        regions.put("BUSAN", "부산");
//        regions.put("JEJU", "제주");
//        model.addAttribute("regions",regions);
        return "form/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/form/items/{itemId}";
    }

}

