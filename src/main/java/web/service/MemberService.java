package web.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.MemberDao;
import web.model.dao.PointlogDao;
import web.model.dto.MemberDto;
import web.model.dto.PointlogDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


@Service
public class MemberService {

    @Autowired  MemberDao memberDao;

    @Autowired PointlogService pointlogService;
    @Autowired PointlogDao pointlogDao;

    // [1] 회원가입
    public boolean OffSidSignup(MemberDto memberDto){
        System.out.println("MemberService.OffSidSignup");
        System.out.println("memberDao = " + memberDao);
        int result =  memberDao.OffSidSignup(memberDto); // 만일 회원가입 성공시 회원번호[PK]반환 , 실패시 0
        if( result >= 1  ){ // 1보다 크면 true 작으면 false
            // 포인트 지급 서비스 호출
            // 1. 방금 위에서 회원가입한 회원번호[PK] 조회 ,
            // 2. 자바에서 오늘 날짜를 문자로 추출 LocalDate 클래스.
            LocalDate localDate = LocalDate.now();

            PointlogDto pointlogDto = new PointlogDto();
            pointlogDto.setPindecrease(10000);
            pointlogDto.setPreason("회원가입");
            pointlogDto.setPstate(1);
            pointlogDto.setMno( result );
            pointlogDto.setAccountlog(null);
            pointlogDto.setPapprovedate(localDate.toString());

            pointlogService.pointPay( pointlogDto );

            return true;
        }else{
            return false;
        }

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
        MemberDto result =  memberDao.mLogin( memberDto );
        if( result.getMno() >= 1 ) { // 만약에 로그인 성공시
            // - 빌더패턴 : 생성자가 아닌 메소드를 이용한 방식의 객체 생성
            MemberDto loginDto = MemberDto.builder()
                    .mno( result.getMno() )
                    .mid( memberDto.getMid() )
                    //헤더에 회원이름과 포인트를 추가
                    .mname(result.getMname())
//                    .pindecrease(memberDto.getPindecrease())

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

        // 1. 현재 로그인된 회원번호 추출
        MemberDto loginDto = loginCheck();
        // 2. 로그인이 안 된 상태이면 false
        if (loginDto == null) return false;
        // 3. 로그인 정보에서 회원번호만 추출
        int loginMno = loginDto.getMno();

        // 4. 로그인된 회원번호를 map 엔트리에 추가
        mUpdateMap.put("mno", String.valueOf(loginMno));

        return memberDao.mUpdate(mUpdateMap);
    }   // mUpdate() end


    // 회원 탈퇴 함수
    public boolean mDelete(String mpw) {
        System.out.println("MemberService.mDelete");
        System.out.println("mpw = " + mpw);

        // 1. 현재 로그인된 회원번호 추출
        MemberDto loginDto = loginCheck();
        // 2. 로그인이 안 된 상태이면 false
        if (loginDto == null) return false;
        // 4. 로그인 정보에서 회원번호만 추출
        int loginMno = loginDto.getMno();

        boolean result = memberDao.mDelete(loginMno, mpw);

        // 회원 탈퇴 성공시 로그아웃 함수 호출
        if (result) {
            logout();
        }

        return result;

    }   // mDelete() end

    //회원 포인트 충전 내역 출력
    public List<PointlogDto> mypointPrint(){
        System.out.println("MemberService.mypointPrint");
        //로그인 세션처리
        MemberDto loginDto = loginCheck(); // 로그인된 세션정보 요청
        System.out.println("loginDto = " + loginDto);
        if( loginDto == null )return null; // 비로그인이면 리턴
        int loginMno = loginDto.getMno();
        return  pointlogDao.mypointPrint(loginMno);


    }

    //포인트 누적 출력
    public int pointAdd(){
        //로그인 세션처리
        MemberDto loginDto = loginCheck(); // 로그인된 세션정보 요청
        System.out.println("loginDto = " + loginDto);
        if( loginDto == null )return 0; // 비로그인이면 리턴
        int loginMno = loginDto.getMno();
        return pointlogDao.pointAdd(loginMno);
    }


}
