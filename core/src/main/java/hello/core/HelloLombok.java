package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("hi");
        helloLombok.setAge(12);

        String name = helloLombok.getName();
        int age = helloLombok.getAge();
        System.out.println("name = " + name);
        System.out.println("age = " + age);
        //toString
        System.out.println("helloLombok = " + helloLombok);
    }

    //롬복 없이는 getter, setter을 alt + insert 를 통해서 만들어 줬어야하는데
    // 롬복을 통해서 그런 메서드를 만들지 않고도 가능하게 한다.

}
