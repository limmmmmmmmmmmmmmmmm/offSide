package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ViewController {

    // ===================== [1] 레이아웃 ===================== //
    @GetMapping("/")    // http://localhost:8080  // 페이지 요청은 HTTP의 GET 방식을 주로 사용된다.
    public String index(){
        return "/index.html";   // templates 폴더내 반환할 경로와 파일명
    }

    // ===================== [2] 회원관련 ===================== //
    //[1] 회원가입 페이지 요청
    @GetMapping("/member/signup")
    public String OffSidSignup(){ return "/member/signup.html"; }
    //[2] 내정보 페이지 요청
    @GetMapping("/member/my/info")
    public String mMyPage(){
        return "/member/myinfo.html";
    }

    //[3] 로그인 페이지 요청
    @GetMapping("/member/login")
    public String mLogin(){
        return "/member/login.html";
    }

    //[4] 회원 수정 페이지 요청
    @GetMapping("/member/update")
    public String mUpdate() {
        return "/member/update.html";
    }

    //[5] 회원 탈퇴 페이지 요청
    @GetMapping("/member/delete")
    public String mDelete() {
        return "/member/delete.html";
    }

    // ==================== [3] 구장관련 ==================== //

    //[1] 게시판 등록 페이지 요청
    @GetMapping("/board/write")
    public String boardWrite(){return  "/board/write.html";}

    //[2] 게시판 전체 출력 페이지 요청
    @GetMapping("/admin/board")
    public String BoardMain(){ return "/board/board.html"; }

    //[3] 게시판 상세 출력 페이지 요청
    @GetMapping("/board/update")
    public String bRead(){return "/board/update.html";}


    // ==================== [4] 관리자 관련 ==================== //
    @GetMapping("/admin/mview")
    public String mPrint(){return "/admin/mprint.html";}

    @GetMapping("/admin/paylist")
    public String apaylist(){return "/admin/paylist.html";}




    // ==================== [5] 포인트 관련 ==================== //
    // 포인트 충전 페이지 요청
    @GetMapping("/point/charge")
    public String pCharge() { return "/point/charge.html"; }

    // 포인트 환불 페이지 요청
    @GetMapping("/point/refund")
    public String pRefund() { return "/point/refund.html"; }



    //==================== [5] 결제 관련 ==================== //
    @GetMapping("/point/history")
    public String myPagepint(){return "/point/mypohistory.html";}


    //==================== [6] 예약 관련 ==================== //
    // 내가 예약한 구장 목록 보기 페이지 요청
    @GetMapping("/reservation/print")
    public String reservationPrint() { return "/reservation/reservationPrint.html"; }

}
