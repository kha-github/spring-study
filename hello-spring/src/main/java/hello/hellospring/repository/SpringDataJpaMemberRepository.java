package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//implement가 implement를 받을 때는 extends
//다중상속
//스프링이 JpaRepository를 상속받는 class는 자동으로 구현체를 만들어서 Bean에 등록해준다
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    //JPOL select m from Member m where m.name = ? 와 동치
    //규칙이 있음
    @Override
    Optional<Member> findByName(String name);

}
