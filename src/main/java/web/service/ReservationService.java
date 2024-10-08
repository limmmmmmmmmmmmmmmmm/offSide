package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import web.model.dao.BoardDao;
import web.model.dao.PointlogDao;
import web.model.dao.ReservationDao;
import web.model.dto.*;

import java.util.List;
import java.util.Map;

import web.model.dto.MemberDto;

import java.time.LocalDate;

@Service
public class ReservationService {
    @Autowired ReservationDao reservationDao;
    @Autowired MemberService memberService;
    @Autowired PointlogService pointlogService;
    @Autowired BoardDao boardDao;
    @Autowired PointlogDao pointlogDao;


    // 내가 예약한 구장 목록 요청
    public List<Map<String, String>> myReservationPrint() {
        System.out.println("ReservationService.reservationListPrint");

        // 현재 로그인된 회원번호 가져오기
        MemberDto loginDto = memberService.loginCheck(); // 로그인된 세션정보 요청
        System.out.println("loginDto = " + loginDto);
        if( loginDto == null )return null; // 비로그인이면 리턴
        int loginMno = loginDto.getMno();

        return reservationDao.myReservationPrint(loginMno);
    }

    // 구장 예약 처리
    public boolean stadiumReservation(int bno){

        // boardDto에 bPrice 넣어주기
        BoardDto boardDto = boardDao.bRead(bno);
        if (boardDto == null)return false;
        int boardPrice = boardDto.getBprice();

        // 로그인 세션 처리
        MemberDto loginDto = memberService.loginCheck();
        if (loginDto == null) return false;
        int loginMto = loginDto.getMno();

        // 만약에 내 포인트가 구장 가격보다 작으면 실패
        int myPoint = pointlogDao.pointAdd(loginMto);
        System.out.println("내 현재포인트 : " + myPoint );
        if( boardPrice > myPoint ){
            return false;
        }

        // [1-3 ] 전체 인원 예약전에 현재 구장 예약인원수 에 따른 제한 , 12명
        int result4 = reservationDao.stadiumReservationCount( bno ); // 현개 구장의 상태가 1(예약중)인 레코드 총 개수 세기 . select count(*) from reservation where bstate = 1 and bno = ?
        if( result4 >= 12 ){ return false; } // 12명 이상이면 예약 실패

        // [1] 구장 예약
        // 내가 예약했던 구장인지 체크
        boolean result3 = reservationDao.stadiumReservationMyCheck(  loginMto , bno ); // 내가 해당 구장을 예약한 기록이 있는지 체크 // select
            if( result3 ){ // 예약한적이 있으면
                // [1-2] 취소 했다가 다시 구장 예약 , 상태 변경
                reservationDao.stadiumReservationUpdate( 1 , loginMto , bno ); // update
            }else{ // 예약한적이 한번도 없던 구장이 였다면
                // [1-1] 최초 구장 예약 , 레코드추가
                boolean result = reservationDao.stadiumReservation( 1 , loginMto , bno );
                if( !result ){ return false; } // 구장예약 실패
        }


        LocalDate localDate = LocalDate.now();// 현재시간 구하는 함수
        // 구장예약 성공
        // [2] 구장 예약 에 따른 포인트 로그 처리
        PointlogDto pointlogDto = new PointlogDto();
        pointlogDto.setPindecrease(-boardPrice);
        pointlogDto.setPreason("구장신청");
        pointlogDto.setPstate(1);
        pointlogDto.setMno( loginMto );
        pointlogDto.setAccountlog(null);
        pointlogDto.setPapprovedate(localDate.toString());
        boolean result2 =  pointlogService.pointPay( pointlogDto );
        return result2;

    }   // stadiumReservation end

    // 구장 취소
    public boolean StadiumCancellation(int bno){

        // board에 bprice 넣어주기
        BoardDto boardDto = boardDao.bRead(bno);
        if (boardDto == null) return false;
        int boardBprice = boardDto.getBprice();

        // 로그인 세션 처리
        MemberDto loginDto = memberService.loginCheck();
        if (loginDto == null) return false;
        int loginMto = loginDto.getMno();

        // [1] 구장 예약 취소
        boolean result = reservationDao.StadiumCancellation( 0 , bno , loginMto );
        System.out.println("result = " + result);
        if( result == false ){ return false; } // 구장예약취소 실패

        // 현재시간 구하는 함수
        LocalDate localDate = LocalDate.now();
        // 구장예약취소 성공
        // [2] 구장 예약 에 따른 포인트 로그 처리
        PointlogDto pointlogDto = new PointlogDto();
        pointlogDto.setPindecrease(boardBprice);
        pointlogDto.setPreason("구장예약취소");
        pointlogDto.setPstate(1);
        pointlogDto.setMno( loginMto );
        pointlogDto.setAccountlog(null);
        pointlogDto.setPapprovedate(localDate.toString());
        boolean result2 =  pointlogService.pointPay( pointlogDto );
        return result2;
    }

    // 구장 예약 유효성 검사
    public boolean effectiveness(int bno ){

        // bno : 구장 번호  -- JS 전달받음
        // mno : 로그인된 회원  -- SPRING 확인
        MemberDto loginDto = memberService.loginCheck();
        if (loginDto == null) return false;
        int mno  = loginDto.getMno();
        int rstate = 1;  // rstate : 예약중인 , 1 -- 고정값
        return reservationDao.effectiveness(bno , mno ,rstate);

    }// effectiveness end

    // 구장 예약 인원수 체크
    public int stadiumReservationCount( int bno ) {
        return reservationDao.stadiumReservationCount(bno); // 현개 구장의 상태가 1(예약중)인 레코드 총 개수 세기 . select count(*) from reservation where bstate = 1 and bno = ?
    }



}   // ReservationService end
