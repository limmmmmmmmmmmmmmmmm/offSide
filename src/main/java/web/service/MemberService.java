package web.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    //  아이디 중복검사
    public boolean mIdCheck( String mid ){
        return memberDao.mIdCheck( mid );
    }


    //[3] 마이페이지
    public MemberDto mMyInfo(){ //MemberDto 타입의 mMyInfo라는 이름의 공용 멤버 변수를 클래스 내에서 선언
        System.out.println("MemberService.mMyInfo"); //확인

        //로그인 세션처리
        MemberDto loginDto = loginCheck(); // 로그인된 세션정보 요청
        System.out.println("loginDto = " + loginDto);
        if( loginDto == null )return null; // 비로그인이면 리턴
        int loginMno = loginDto.getMno();

        return memberDao.mMyInfo(loginMno);
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
                    .mno( result )
                    .mid( memberDto.getMid() )
                    .build();
            HttpSession session = request.getSession();
            session.setAttribute( "loginDto", loginDto );
            return true;
        }
        return false;
    }

    // 로그인의 상태 반환
    public MemberDto loginCheck( ){
        HttpSession session = request.getSession(); // 1. 현재 요청을 보내온 클라이언트의 세션객체호출
        // 2. 세션객체내 속성 값 호출 , 타입변환 필요하다.
        Object object = session.getAttribute( "loginDto" );
        if( object !=null ){   return (MemberDto)object;  }
        return null;
    }

    // 4. 로그아웃 : 세션 초기화
    public void logout( ){
        // 1. 현재 요청을 보내온 클라이언트의 세션객체호출
        HttpSession session = request.getSession();
        // 2. 세션객체내 모든 속성 값 초기화
        session.invalidate();
    }




    // 회원 수정 함수
    public boolean mUpdate(Map<String, String> mUpdateMap) {
        System.out.println("MemberService.mUpdate");
        System.out.println("mUpdateMap = " + mUpdateMap);

        // map에 현재 로그인돼있는 회원번호 추가해야 함. 이거 임시.
        int loginMno = 1;
        mUpdateMap.put("mno", String.valueOf(loginMno));

        return memberDao.mUpdate(mUpdateMap);
    }   // mUpdate() end


    // 회원 탈퇴 함수
    public boolean mDelete(String mpw) {
        System.out.println("MemberService.mDelete");
        System.out.println("mpw = " + mpw);

        // 로그인된 회원 번호 받아오기 추가해야 함
        int loginMno = 5;

        return memberDao.mDelete(loginMno, mpw);

        // 회원 탈퇴 성공시 로그아웃 함수 호출하는 거 추가해야 함


    }   // mDelete() end

}
