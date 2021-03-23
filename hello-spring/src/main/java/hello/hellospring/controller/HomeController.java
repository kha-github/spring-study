package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// HomeController 안에 맵핑이 되어있기 때문에 default로 사용된 index.html이 아닌 home.html이 더 우선순위로 올라온다
@Controller
public class HomeController {
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
