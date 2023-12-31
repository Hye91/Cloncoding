package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.item.SaveCheck;
import hello.itemservice.domain.item.UpdateCheck;
import hello.itemservice.web.validation.form.ItemSaveForm;
import hello.itemservice.web.validation.form.ItemUpdateForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/validation/v4/items")
@RequiredArgsConstructor
@Slf4j
public class ValidationItemControllerV4 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v4/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v4/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v4/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated @ModelAttribute("item") ItemSaveForm form, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Model model) {
        //@ModelAttribute("item")을 하지않으면 itemSaveForm, form이 model에 담기게 되는데 그럼
        //resources에 값도 전부 바꿔야하기때문에 그대로 item으로 유지시켜주기위해서 이렇게 해둔다
        
        //특정 필드가 아닌 복합 룰 검증 (globalError)
        //objectError은 값이 남아있는게 없기때문에 rejectedValue나 bindingFailure 추가하지않는다
        if(form.getQuantity() != null && form.getPrice() != null){
            int resultPrice = form.getPrice() * form.getQuantity();
            if(resultPrice < 10000){
                bindingResult.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
                //reject는 Object , Object 이름은 bindingResult가 이미 알고 있기때문에 여기서 끝
            }
        }

        //검증에 실패하면 다시 입력 폼으로! 오류값또한 같이 입력폼에 보이도록 보내줘야한다.
        if(bindingResult.hasErrors()/*!errors.isEmpty()*/){
            log.info("errors ={} " , bindingResult);
            return "validation/v4/addForm"; //검증 실패시 그냥 입력뷰로 보내버리는것
        }

        //성공 로직
        //전송 받은 form의 값을 item에 set을 하고 이 값을 itemRepository에 저장하는 방식으로 변경하였다.
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setPrice(form.getPrice());
        item.setQuantity(form.getQuantity());

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v4/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v4/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated @ModelAttribute("item") ItemUpdateForm form, BindingResult bindingResult) {

        //특정 필드가 아닌 복합 룰 검증 (globalError)
        //objectError은 값이 남아있는게 없기때문에 rejectedValue나 bindingFailure 추가하지않는다
        if(form.getQuantity() != null && form.getPrice() != null){
            int resultPrice = form.getPrice() * form.getQuantity();
            if(resultPrice < 10000){
                bindingResult.reject("totalPriceMin",new Object[]{10000,resultPrice},null);
                //reject는 Object , Object 이름은 bindingResult가 이미 알고 있기때문에 여기서 끝
            }
        }

        //오류가 있는 경우 폼으로 돌아가기
        if(bindingResult.hasErrors()){
            log.info( "errors = {}" + bindingResult);
            return "validation/v4/editForm";
        }

        Item itemParam = new Item(); //update할때는 item아니고 updateParam을 사용한다! 각 메서드에 맞는 파라미터 확인 잘 하기
        itemParam.setItemName(form.getItemName());
        itemParam.setPrice(form.getPrice());
        itemParam.setQuantity(form.getQuantity());
        itemRepository.update(itemId, itemParam);
        return "redirect:/validation/v4/items/{itemId}";
    }

}

