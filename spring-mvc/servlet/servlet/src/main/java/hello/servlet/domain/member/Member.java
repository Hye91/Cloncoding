package hello.servlet.domain.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member { //서블릿을 이용한 회원관리 어플리케이션 만들기

    private Long id; //레파지토리에 저장하면 id가 발급되게된다.
    private String username;
    private int age;

    public Member() { //기본 빈 생성자
    }

    public Member(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
