package hello.upload.controller;

import hello.upload.domain.Item;
import hello.upload.domain.ItemRepository;
import hello.upload.domain.UploadFile;
import hello.upload.file.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    @GetMapping("/items/new")
    public String NewItem(@ModelAttribute ItemForm form){
        return "item-form";
    }

    @PostMapping("/items/new") //파일이 실제 저장되는 경로
    public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {
        //하나의 파일 등록
        UploadFile attachFile = fileStore.stoerFile(form.getAttachFile());

        //여러개의 이미지 등록
        List<UploadFile> storeImageFiles = fileStore.stoerFiles(form.getImageFiles());

        //데이터 베이스에 저장
        Item item = new Item();
        item.setItemName(form.getItemName());
        item.setAttachFile(attachFile);
        item.setImageFiles(storeImageFiles);
        itemRepository.save(item);

        //보통 파일의 경우 DB에 저장하지 않는다. 경로의 이름을 저장하는거지 파일 자체는 저장X
        //AWS같은걸 사용하면 S3에 저장하는 방식.

        redirectAttributes.addAttribute("itemId", item.getId());

        return "redirect:/items/{itemId}";
    }

    //등록한 전체 파일 보여주기
    @GetMapping("/items/{id}")
    public String items(@PathVariable(name = "id") Long id, Model model){
        Item item = itemRepository.findById(id);
        model.addAttribute("item", item);
        return "item-view";
    }

    //이미지 보여주기
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable(name = "filename")String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
        /**
         * 보안에 신경쓰려면 여러가지 체크 로직을 넣는게 좋다
         */
    }

    //첨부파일 다운로드하기
    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> /*@ResponseBody 안쓰는 다른 방법*/ downloadAttach(@PathVariable(name = "itemId") Long itemId) throws MalformedURLException {
        //item에 접근할수있는 사용자만 파일을 다운로드 할수있게 만드는 방법
        Item item = itemRepository.findById(itemId);
        String storeFileName = item.getAttachFile().getStoreFileName(); //시스템에 저장되어 있는 파일 이름
        String uploadFileName = item.getAttachFile().getUploadFileName();//사용자가 업로드 할 당시의 파일 이름

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        log.info("uploadFileName={}", uploadFileName);

        //이 코드를 추가하지 않으면 파일이 다운로드 되는게 아니라
        //미리보기처럼 파일이 열려버리게 된다.

        //한글로 된 파일의 경우 깨질수 있는데 그거 보완하는 방법
        String encodedUploadFileName  = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

}
