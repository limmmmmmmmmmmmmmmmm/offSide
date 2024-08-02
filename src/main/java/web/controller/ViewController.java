package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {


    // [1] 회원가입
    @GetMapping("/member/signup")
    public String OffSidSignup(){ return "/member/signup.html"; }
}
