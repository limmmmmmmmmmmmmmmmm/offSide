package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.MemberDto;
import web.model.dto.PointlogDto;
import web.service.MemberService;

import java.util.Map;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    MemberService memberService;

    // [1] 회원가입
    @PostMapping("/signup")
    public boolean OffSidSignup(MemberDto memberDto){
        System.out.println("MemberController.OffSidSignup");
        System.out.println("memberService = " + memberService);
        return memberService.OffSidSignup(memberDto);
    }

    // 아이디 중복검사
    @GetMapping("/idcheck")
    public boolean mIdCheck( String mid ){
        return memberService.mIdCheck( mid );
    }


    // 마이페이지 정보
    @GetMapping("/my/info2") //
    public MemberDto mMyInfo(){
        System.out.println("MemberController.mMyInfo");
        System.out.println("memberService = " + memberService);
        return memberService.mMyInfo();
    }


    // 로그인 함수
    @PostMapping("/login")
    public boolean mLogin( MemberDto memberDto ){
        System.out.println("MemberController.mLogin");
        System.out.println("memberDto = " + memberDto);
        return memberService.mLogin( memberDto );
    }

    // 로그인 체크
    @GetMapping("/login/check")
    public MemberDto mLoginCheck( ){
        return memberService.loginCheck();
    }

    // 로그아웃
    @GetMapping("/logout")
    public void logout(){
        memberService.logout();
    }


    // 회원 수정 함수
    @PutMapping("/update")
    public boolean mUpdate(@RequestBody Map<String, String> mUpdateMap) {
        System.out.println("MemberController.mUpdate");
        System.out.println("mUpdateMap = " + mUpdateMap);
        return memberService.mUpdate(mUpdateMap);
    }   // mUpdate() end

    // 회원 탈퇴 함수
    @DeleteMapping("/delete")
    public boolean mDelete(String mpw) {
        System.out.println("MemberController.mDelete");
        System.out.println("mpw = " + mpw);
        return memberService.mDelete(mpw);
    }   // mDelete() end


//    //회원 포인트 내역 출력
//    @GetMapping("/history")
//    public PointlogDto mypointPrint(){
//        return  memberService.mypointPrint();
//
//    }



}
