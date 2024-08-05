package web.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;




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
    //  아이디 중복검사
    public boolean mIdCheck( String id ){
        return memberDao.mIdCheck( id );
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




}
