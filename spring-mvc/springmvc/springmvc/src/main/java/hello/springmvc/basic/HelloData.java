package hello.springmvc.basic;

import lombok.Data;

@Data //getter, setter, toString을 자동으로 완성해준다
public class HelloData {
    private String username;
    private int age;
}
