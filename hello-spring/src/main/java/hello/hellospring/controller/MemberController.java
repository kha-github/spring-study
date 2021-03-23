package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    // URL로 직접 맵핑 : GET 방식, 데이터를 클라이언트로 전달할때
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    // 데이터등을 form으로 넣어서 서버로 전달할 때
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMember();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
