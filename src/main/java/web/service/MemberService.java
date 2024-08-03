package web.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

import java.util.Map;


@Service
public class MemberService {
    @Autowired
    MemberDao memberDao;


    // [1] 회원가입
    public boolean OffSidSignup(MemberDto memberDto){
        System.out.println("MemberService.OffSidSignup");
        System.out.println("memberDao = " + memberDao);
        return memberDao.OffSidSignup(memberDto);
    }



    //[3] 마이페이지
    public MemberDto mMyInfo(){
        System.out.println("MemberService.mMyInfo");
        return memberDao.mMyInfo();
    }

    // 세선 객체 호출
    @Autowired
    HttpServletRequest request;
    public boolean mLogin( MemberDto memberDto ){
        System.out.println("MemberService.mLogin"); System.out.println("memberDto = " + memberDto);
        int result =  memberDao.mLogin( memberDto );
        if( result >= 1 ) { // 만약에 로그인 성공시
            // - 빌더패턴 : 생성자가 아닌 메소드를 이용한 방식의 객체 생성
            MemberDto loginDto = MemberDto.builder()
                    .mno( String.valueOf(result) )
                    .mid( memberDto.getMid() )
                    .build();
            HttpSession session = request.getSession();
            session.setAttribute( "loginDto", loginDto );
            return true;
        }
        return false;
    }


    // 회원 수정 함수
    public boolean mUpdate(Map<String, String> mUpdateMap) {
        System.out.println("MemberController.mUpdate");
        System.out.println("mUpdateMap = " + mUpdateMap);

        // map에 현재 로그인돼있는 회원번호 추가해야 함. 이거 임시.
        int loginMno = 1;
        mUpdateMap.put("mno", String.valueOf(loginMno));

        return memberDao.mUpdate(mUpdateMap);
    }   // mUpdate() end

}
