package hello.upload.domain;

import lombok.Data;

import java.util.List;

@Data
public class Item { //관리할 item 객체

    private Long id; //DB에 저장되는 서버에서 관리하는 id
    private String itemName;
    private UploadFile attachFile;
    private List<UploadFile> imageFiles; //이미지의 경우 하나가 아닌 여러개의 파일을 올릴수 있어야한다
}
