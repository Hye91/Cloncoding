package hello.hellospring.controller;

public class MemberForm {

    private String name;
    //createMemberForm의 name과 매칭이 된다.

    //alt + insert 게터 세터 만들기
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
