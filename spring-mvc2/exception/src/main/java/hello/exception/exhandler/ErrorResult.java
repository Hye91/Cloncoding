package hello.exception.exhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResult { //오류가 발생했을때의 format을 구성하는건가
    //팀 프로젝트할때 ResponseDto에서 했던게 이거구만

    private String code;
    private String message;


}
