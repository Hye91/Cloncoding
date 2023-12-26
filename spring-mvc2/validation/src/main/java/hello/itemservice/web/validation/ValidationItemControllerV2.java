package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
@Slf4j
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

    //@PostMapping("/add")
    public String addItemV1(@ModelAttribute Item item, BindingResult bindingResult,
                          RedirectAttributes redirectAttributes, Model model) {
        //검증 오류가 발생시 오류값을 모델에 다시 담아서 입력폼으로 보내줘야한다. 그래서 Model을 추가함.
        //ModelAttribute를 통해서 오류가 생기는 값일지라도 item에 담기게 된다. 그 상태 그대로
        //addForm 으로 진행되기때문에 오류가 생기는 값이 그대로 화면에 남게되는것이다.(재사용가능)

        //bindingResult : item에 binding된 결과가 담기게 된다(오류가 생성되도 컨트롤러 호출이 되게한다)
        //검증 시에 무슨 오류가 있는지 담아주는 객체가 필요하다. 문제에 대한 결과를 담아준다
//        Map<String, String> errors = new HashMap<>();
        //bindingResult가 errors의 역할을 해주게 된다.
        //웹 애플리케이션의 폼에서 사용자가 입력한 데이터를 처리할 때, 특정 입력 필드에 부적절한 값이 들어갈 경우 필드 단위 에러가 발생할 수 있다.

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            //errors.put("itemName", "상품 이름은 필수입니다.");
            bindingResult.addError(new FieldError("item","itemName", "상품 이름은 필수입니다."));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            //errors.put("price", "가격은 1,000 ~ 1,000,000 까지 허용합니다");
            bindingResult.addError(new FieldError("item","price", "가격은 1,000 ~ 1,000,000 까지 허용합니다"));
        }
        if(item.getQuantity() == null || item.getQuantity() >= 9999){
            //errors.put("quantity"," 수량은 최대 9,999까지 허용합니다");
            bindingResult.addError(new FieldError("item","quantity", "수량은 최대 9,999까지 허용합니다"));
        }

        //특정 필드가 아닌 복합 룰 검증 (globalError)
        if(item.getQuantity() != null && item.getPrice() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                //errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                bindingResult.addError(new ObjectError("item","가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
            }
        }

        //검증에 실패하면 다시 입력 폼으로! 오류값또한 같이 입력폼에 보이도록 보내줘야한다.
        if(bindingResult.hasErrors()/*!errors.isEmpty()*/){
            log.info("errors ={} " , bindingResult);
            //model.addAttribute("errors", errors); bindingResult(자동으로 view에 넘김)에 담아내기때문에 여기 담지 않아도 된다.
            return "validation/v2/addForm"; //검증 실패시 그냥 입력뷰로 보내버리는것
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            //errors.put("itemName", "상품 이름은 필수입니다.");

            //입력값 오류 이후에도 그 값 남겨놓는 방법
            bindingResult.addError(new FieldError("item","itemName",item.getItemName(),false,null,null, "상품 이름은 필수입니다."));
            //bindingFailure : 바인딩 연결 자체에 실패를 했는지에 대한 걸 물어보는거라서 이건 연결 실패의 경우가 아니므로 false로
            //rejectedValue : 사용자가 입력한 타입이 맞지 않는 값 -> 오류가 생성되면 이게 bingingResult에 담겨서
            // (field Error을 만듬)컨트롤러 호출전에 스프링에 저장되어서 남게된다
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            //errors.put("price", "가격은 1,000 ~ 1,000,000 까지 허용합니다");
            //bindingResult.addError(new FieldError("item","price", "가격은 1,000 ~ 1,000,000 까지 허용합니다"));
            bindingResult.addError(new FieldError("item","price", item.getPrice(), false, null, null, "가격은 1,000 ~ 1,000,000 까지 허용합니다"));
        }
        if(item.getQuantity() == null || item.getQuantity() >= 9999){
            //errors.put("quantity"," 수량은 최대 9,999까지 허용합니다");
            //bindingResult.addError(new FieldError("item","quantity", "수량은 최대 9,999까지 허용합니다"));
            bindingResult.addError(new FieldError("item","quantity",item.getQuantity(),false,null,null, "수량은 최대 9,999까지 허용합니다"));
        }

        //특정 필드가 아닌 복합 룰 검증 (globalError)
        //objectError은 값이 남아있는게 없기때문에 rejectedValue나 bindingFailure 추가하지않는다
        if(item.getQuantity() != null && item.getPrice() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                //errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice);
                bindingResult.addError(new ObjectError("item",null,null,"가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultPrice));
            }
        }

        //검증에 실패하면 다시 입력 폼으로! 오류값또한 같이 입력폼에 보이도록 보내줘야한다.
        if(bindingResult.hasErrors()/*!errors.isEmpty()*/){
            log.info("errors ={} " , bindingResult);
            return "validation/v2/addForm"; //검증 실패시 그냥 입력뷰로 보내버리는것
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    //@PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {

        log.info("objectName={} ", bindingResult.getObjectName());
        log.info("target={}", bindingResult.getTarget());

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            //errors.put("itemName", "상품 이름은 필수입니다.");

            //입력값 오류 이후에도 그 값 남겨놓는 방법
            //code에 각각의 defaultMessage를 저장해둔것을 가져와서 사용하게 한다.(argument는 배열의 범위를 표현하는건가)
            //message의 값을 한데 모아서 사용한것과 같은 방식
            bindingResult.addError(new FieldError("item","itemName",item.getItemName(),false, new String[]{"required.item.itemName"},null, null));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            
            bindingResult.addError(new FieldError("item","price", item.getPrice(), false, new String[]{"range.item.price"},new Object[]{1000,1000000}, null));
        }
        if(item.getQuantity() == null || item.getQuantity() >= 9999){
            
            bindingResult.addError(new FieldError("item","quantity",item.getQuantity(),false,new String[]{"max.item.quantity"},new Object[]{9999}, null));
        }

        //특정 필드가 아닌 복합 룰 검증 (globalError)
        //objectError은 값이 남아있는게 없기때문에 rejectedValue나 bindingFailure 추가하지않는다
        if(item.getQuantity() != null && item.getPrice() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                
                bindingResult.addError(new ObjectError("item",new String[]{"totalPriceMin"},new Object[]{10000,resultPrice},null));
            }
        }

        //검증에 실패하면 다시 입력 폼으로! 오류값또한 같이 입력폼에 보이도록 보내줘야한다.
        if(bindingResult.hasErrors()/*!errors.isEmpty()*/){
            log.info("errors ={} " , bindingResult);
            return "validation/v2/addForm"; //검증 실패시 그냥 입력뷰로 보내버리는것
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item, BindingResult bindingResult,
                            RedirectAttributes redirectAttributes, Model model) {

        log.info("objectName={} ", bindingResult.getObjectName());
        log.info("target={}", bindingResult.getTarget());

        //검증 로직
        if(!StringUtils.hasText(item.getItemName())){
            //errors.put("itemName", "상품 이름은 필수입니다.");

            //입력값 오류 이후에도 그 값 남겨놓는 방법
            //code에 각각의 defaultMessage를 저장해둔것을 가져와서 사용하게 한다.(argument는 배열의 범위를 표현하는건가)
            //message의 값을 한데 모아서 사용한것과 같은 방식
            bindingResult.addError(new FieldError("item","itemName",item.getItemName(),false, new String[]{"required.item.itemName"},null, null));
        }
        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){

            bindingResult.addError(new FieldError("item","price", item.getPrice(), false, new String[]{"range.item.price"},new Object[]{1000,1000000}, null));
        }
        if(item.getQuantity() == null || item.getQuantity() >= 9999){

            bindingResult.addError(new FieldError("item","quantity",item.getQuantity(),false,new String[]{"max.item.quantity"},new Object[]{9999}, null));
        }

        //특정 필드가 아닌 복합 룰 검증 (globalError)
        //objectError은 값이 남아있는게 없기때문에 rejectedValue나 bindingFailure 추가하지않는다
        if(item.getQuantity() != null && item.getPrice() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){

                bindingResult.addError(new ObjectError("item",new String[]{"totalPriceMin"},new Object[]{10000,resultPrice},null));
            }
        }

        //검증에 실패하면 다시 입력 폼으로! 오류값또한 같이 입력폼에 보이도록 보내줘야한다.
        if(bindingResult.hasErrors()/*!errors.isEmpty()*/){
            log.info("errors ={} " , bindingResult);
            return "validation/v2/addForm"; //검증 실패시 그냥 입력뷰로 보내버리는것
        }

        //성공 로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}

