package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 컴포넌트 스캔 방식
@Controller
public class MemberController {

    // 여러개 생성하지 않고 하나를 공용으로 쓰면 됨. new를 할 필요가 없다.
    // private final MemberService memberService = new MemberService();

    // 필드 주입 방식
    // 중간에 바꿀 수 있는 방법이 없으므로 잘 사용하지 않음
    // @Autowired private MemberService memberService;

    private final MemberService memberService;

    // setter 주입 방식
    // 생성 후에 나중에 setter을 호출하여 DI 진행
    // 후에 변경될 여지가 없음에도 setMemberService를 위해서 public하게 노출되어야만 동작함
//    @Autowired
//    public void setMemberService(MemberService memberService){
//        this.memberService = memberService;
//    }

    // 컨트롤러는 서비스에 의존중
    // Service 객체를 컨테이너에서 찾아 연결해주는 역할 : Autowired
    // 이를 위해서 Service는 이미 컨테이너에 구현되어있어야 한다 (=객체가 @Service로 어노테이션이 달려있어야한다)
    // 생성자를 통해서 멤버 서비스를 주입한다 => 생성자 주입 방식, 제일 선호된다. 한번 조립한 이후에는 변경할 수 없다
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
