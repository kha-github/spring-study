package hello.hellospring.controller;

public class MemberForm {
    // html의 name태그가 "name"인 것을 보고 자동으로 매칭해줌
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
