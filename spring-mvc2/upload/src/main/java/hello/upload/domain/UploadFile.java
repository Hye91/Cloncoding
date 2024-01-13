package hello.upload.domain;

import lombok.Data;

@Data
public class UploadFile { //업로드한 파일을 보관하는 객체

    private String uploadFileName; //업로드 할때의 파일 이름
    private String storeFileName; //시스템 저장될때의 파일 이름
    //다른 사용자들이 같은 파일명을 이용해서 저장을 하는 경우 시스템에서는 다른 이름으로 저장되어야 한다.

}
