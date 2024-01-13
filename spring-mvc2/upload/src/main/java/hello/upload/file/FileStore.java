package hello.upload.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir; //지정한 경로에 file을 저장하게 된다

    public String gerFullPath(String filename){ //file의 full이름을 받아내는 메서드
        return fileDir + filename;
    }

    //파일 저장

}
