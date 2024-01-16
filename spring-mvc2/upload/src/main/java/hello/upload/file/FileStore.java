package hello.upload.file;

import hello.upload.domain.UploadFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {

    @Value("${file.dir}")
    private String fileDir; //지정한 경로에 file을 저장하게 된다

    public String getFullPath(String filename){ //file의 full이름을 받아내는 메서드
        return fileDir + filename;
    }

    //파일 여러개 저장
    public List<UploadFile> stoerFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> storeFileResult = new ArrayList<>();
        //uploadFile이 계속 생성되므로 list에 담아줘야한다.
        for (MultipartFile multipartFile : multipartFiles) {
            if(!multipartFile.isEmpty()){
//                UploadFile uploadFile = stoerFile(multipartFile);//파일 하나 저장
//                storeFileResult.add(uploadFile);//List에 저장
                storeFileResult.add(stoerFile(multipartFile));

            }
        }
        return storeFileResult;
    }

    //파일 하나 저장
    public UploadFile stoerFile(MultipartFile multipartFile) throws IOException {
        if(multipartFile.isEmpty()){
            return null;
        }
        //사용자가 업로드한 파일 이름
        String originalFilename = multipartFile.getOriginalFilename();
        //서버에 저장하는 파일명 메서드
        String storeFileName = createStoreFileName(originalFilename);

        multipartFile.transferTo(new File(getFullPath(storeFileName))); //파일 저장

        return new UploadFile(originalFilename, storeFileName);
    }

    private String createStoreFileName(String originalFilename) {
        String uuid = UUID.randomUUID().toString();
        String ext = extracteExt(originalFilename);
        return uuid + "." + ext;
    }

    private String extracteExt(String originalFilename) {//OriginalFileName에서 확장자명만 떼오는 메서드
        //ex) 저장하는 Original이름이 image.png일 경우
        //서버에 저장되는 이름은 uuid를 사용해서 저장한 다음 뒤의 확장자명만 붙이게 하려고한다.
        int pos = originalFilename.lastIndexOf(".");// .이 위치한 인덱스 뽑아내기
        return originalFilename.substring(pos + 1); //확장자명 가져오기
    }


}
