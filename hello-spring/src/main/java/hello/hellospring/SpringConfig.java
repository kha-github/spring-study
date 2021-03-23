package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// java configuration 방식을 사용하면 이 곳에서 바꿔치기 하고 싶은 부분만 수정하면 된다
// 컴포넌트 스캔 방식을 사용하면 여러 부분을 다 바꿔주어야 함
@Configuration
public class SpringConfig {

    // 스프링 빈에 아래의 로직을 호출하여 등록해줌
    // 서비스는 레포지토리가 필요하다
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    // 스프링 빈에 레포지토리 올려줌
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
