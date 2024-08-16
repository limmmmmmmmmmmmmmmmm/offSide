package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.ReservationDao;
import web.model.dto.MemberDto;

import java.util.List;
import java.util.Map;

@Service
public class ReservationService {
    @Autowired ReservationDao reservationDao;
    @Autowired MemberService memberService;

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

}
