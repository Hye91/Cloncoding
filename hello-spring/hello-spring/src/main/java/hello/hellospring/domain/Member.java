package hello.hellospring.domain;

import javax.persistence.*;

@Entity //jpa를 사용할때 이 어노테이션을 추가하면 이제 jpa가 관리하는 entity가 되는것.
public class Member {

    //jpa : java 인터페이스의 표준, 구현을 여러업체에서 한다고 생각하면 된다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //고객이정하는 아이디 아니고 시스템이 저장하는 아이디

    //@Column(name = "user") DB에 User라는 이름으로 컬럼명 저장시 이렇게 맵핑한다.
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
