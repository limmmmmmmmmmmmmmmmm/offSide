package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.dto.MemberDto;
import web.service.MemberService;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    // [1] 회원가입
    @PostMapping("/member/signup")
    public boolean OffSidSignup(MemberDto memberDto){
        System.out.println("MemberController.OffSidSignup");
        System.out.println("memberService = " + memberService);

        return memberService.OffSidSignup(memberDto);
    }




}
