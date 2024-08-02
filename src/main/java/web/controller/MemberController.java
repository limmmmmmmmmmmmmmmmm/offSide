package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.MemberDto;
import web.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
@RestController
@RequestMapping("/member")
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


    // 마이페이지 정보
    @GetMapping("/my/info2")
    public MemberDto mMyInfo(){
        System.out.println("MemberController.mMyInfo");
        System.out.println("memberService = " + memberService);
        return memberService.mMyInfo();
    }
}
