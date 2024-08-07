package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    // ===================== [1] 레이아웃 ===================== //
    @GetMapping("/")    // http://localhost:8080  // 페이지 요청은 HTTP의 GET 방식을 주로 사용된다.
    public String index(){
        return "/index.html";   // templates 폴더내 반환할 경로와 파일명
    }

    // ===================== [2] 회원관련 ===================== //
    // [1] 회원가입
    @GetMapping("/member/signup")
    public String OffSidSignup(){ return "/member/signup.html"; }

    // 6. 게시판(구장) 수정 페이지 요청
    @GetMapping("/board/update")
    public String bUpdate(){return "/board/update.html"; }


    @GetMapping("/member/my/info")
    public String mMyPage(){
        return "/member/myinfo.html";
    }

    //로그인 매핑
    @GetMapping("/member/login")
    public String mLogin(){
        return "/member/login.html";
    }

    // 4. 회원 수정 페이지 요청
    @GetMapping("/member/update")
    public String mUpdate() {
        return "/member/update.html";
    }

    // 5. 회원 탈퇴 페이지 요청
    @GetMapping("/member/delete")
    public String mDelete() {
        return "/member/delete.html";
    }

    // 게시판 삭제
    @GetMapping("/board/delete")
    public String bDelete(){
        return "/board/delete.html";
    }

    // ==================== [3] 구장관련 ==================== //
    @GetMapping("/admin/board")
    public String BoardMain(){ return "/board/board.html"; }

}
