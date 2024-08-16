package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import web.model.dao.BoardDao;
import web.model.dao.ReservationDao;
import web.model.dto.MemberDto;

import java.util.List;
import java.util.Map;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;
import web.model.dto.PointlogDto;

import java.time.LocalDate;

@Service
public class ReservationService {
    @Autowired ReservationDao reservationDao;
    @Autowired MemberService memberService;

    // 내가 예약한 구장 목록 요청
    public List<Map<String, String>> myReservationPrint() {
        System.out.println("ReservationService.reservationListPrint");
    @Autowired MemberService memberService;
    @Autowired PointlogService pointlogService;
    @Autowired BoardDao boardDao;

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
//        int myPoint = ????
//        if( boardPrice > myPoint ){
//            return false;
//        }

        // [1] 구장 예약
        boolean result = reservationDao.stadiumReservation( 1 , loginMto , bno );
        if( result == false ){ return false; } // 구장예약 실패

        // 구장예약 성공
        // [2] 구장 예약 에 따른 포인트 로그 처리
            // 현재시간 구하는 함수
            LocalDate localDate = LocalDate.now();
            PointlogDto pointlogDto = new PointlogDto();
            pointlogDto.setPindecrease(-boardPrice);
            pointlogDto.setPreason("구장신청");
            pointlogDto.setPstate(1);
            pointlogDto.setMno( loginMto );
            pointlogDto.setAccountlog(null);
            pointlogDto.setPapprovedate(localDate.toString());
            boolean result2 =  pointlogService.pointPay( pointlogDto );
            return result2;

        // 현재 로그인된 회원번호 가져오기
        MemberDto loginDto = memberService.loginCheck(); // 로그인된 세션정보 요청
        System.out.println("loginDto = " + loginDto);
        if( loginDto == null )return null; // 비로그인이면 리턴
        int loginMno = loginDto.getMno();

        return reservationDao.myReservationPrint(loginMno);
    }
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
        // rstate : 예약중인 , 1 -- 고정값
        MemberDto loginDto = memberService.loginCheck();
        if (loginDto == null) return false;
        int mno  = loginDto.getMno();
        int rstate = 1;
        return reservationDao.effectiveness(bno , mno ,rstate);

    }// effectiveness end




}   // ReservationService end
