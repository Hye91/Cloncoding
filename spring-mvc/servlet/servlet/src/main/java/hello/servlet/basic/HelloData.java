package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HelloData { //api형식의 body를 위한 클래스

    private String username;
    private int age;

}
