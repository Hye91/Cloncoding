package hello.upload.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
@Controller
@RequestMapping("servlet/v2")
public class ServletUploadControllerV2 {

    //업로드한 file을 저장하기 위해서 설정한 경로가져오기
    @Value("${file.dir}") //application.properties의 속성을 그대로 가져올수 있게된다
    private String fileDir;


    @GetMapping("/upload")
    public String newFile(){
        return "upload-form";
    }

    @PostMapping("/upload")
    public String saveFileV1(HttpServletRequest request) throws ServletException, IOException {
        log.info("request={}", request);

        String itemName = request.getParameter("itemName");
        log.info("itemName={}", itemName);

        //Parts , servlet file upload를 사용하기 위한 단계 시작
        //multiPart 업로딩을 위해서 각각의 유형에따라 구별해서 넣어줄수 있게한다
        Collection<Part> parts = request.getParts();
        log.info("parts={}",parts);
        //parts 를 사용한다
        for (Part part : parts) {
            log.info("===PART===");
            log.info("name={}",part.getName());
            Collection<String> headerNames = part.getHeaderNames();
            //parts도 Header와 Body로 구분이 된다. 그래서 각각의 파츠들의 헤더값을 가져온다
            for (String headerName : headerNames) {
                log.info("header {}: {}",headerName,part.getHeader(headerName));
            }
            //편의 메서드
            //content-disposition ; fileName
            log.info("submittedFileName={}",part.getSubmittedFileName());
            log.info("size={}",part.getSize()); //parts의 Body 크기를 볼수 있다.

            //데이터를 읽기 (Body에 있는 data 읽기)
            //Body에 있는 binary 글자들을 읽으려고 하는것. 이걸 읽는게 어려우니까
            //StreamUtil을 사용해서 읽게된다. 이때 StandardCharsets.UTF_8을 지정한다.
            InputStream inputStream = part.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            log.info("body ={}", body);

            //파일에 저장하기
            if(StringUtils.hasText(part.getSubmittedFileName())){
                String fullPath = fileDir + part.getSubmittedFileName();
                log.info("파일 저장 fullPath={}",fullPath);
                part.write(fullPath); //실제 파일이 저장되는 경로 로직

            }

        }

        return "upload-form";
    }
}
