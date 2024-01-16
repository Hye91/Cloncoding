package hello.upload.controller;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemForm { //데이터를 전달하는 form

    private long itemId;
    private String itemName;
    private MultipartFile attachFile;
    private List<MultipartFile> imageFiles;

    //item 클래스에서는 UpLoadFile을 반환했는데 왜 전달하는 form에서는 MultiPartFile을 하는거지?

}
