package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// Transactional 어노테이션을 달지 않으면 테스트를 반복적으로 수행할 수 없음
// 해당 어노테이션을 이용하여 테스트할 때 사용한 쿼리들을 commit하지 않고 테스트 후에 rollback 시켜 DB에 저장하지 않음
// 따라서 다시 테스트를 했을 때에 DB 잔여 데이터 문제로 이슈가 생기지 않게 된다
@Transactional
class MemberServiceIntegrationTest {

    // 소스코드에서는 Autowired를 안 쓰느것이 좋지만, Test는 개발 코드가 아니므로 그냥 편한대로 적는다
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void duplicate_member_exception(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        // 2번째 인자를 실행하면 첫번째 예외가 발생하는지 확인
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }
}