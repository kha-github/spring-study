package hello.hellospring.domain;

import javax.persistence.*;

//JPA가 관리하는 엔티티임을 명시
@Entity
public class Member {

    // @Id : 해당 키가 PK임을 명시
    // identity 전략을 통해 해당 엔티티의 id 컬럼은 auto increment이기 때문에 DB가 자동으로관리해 줘야함을 명시
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
