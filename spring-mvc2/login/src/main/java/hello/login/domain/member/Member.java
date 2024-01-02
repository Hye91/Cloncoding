package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Member {

    private Long id; //DB저장되서 관리되어 지는 id
    @NotEmpty
    private String loginId; //사용자가 입력할때 사용하는 ID
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;

}
