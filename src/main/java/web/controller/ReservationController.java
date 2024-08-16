package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.service.ReservationService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired ReservationService reservationService;

    // 구장 예약
    @PostMapping("/stadiumreservation") //   구장예약 신청 URL : /reservation/stadiumreservation?bno=구장번호
    public boolean stadiumReservation(int bno){

        return reservationService.stadiumReservation(bno);
    } // end

    // 구장 취소
    @PutMapping("/stadiumcancellation")
    public boolean StadiumCancellation(int bno){

        return reservationService.StadiumCancellation(bno);
    }// StadiumCancellation end
    // 내가 예약한 구장 목록 요청
    @GetMapping("/my/print")
    public List<Map<String, String>> myReservationPrint() {
        System.out.println("ReservationController.reservationListPrint");

        return reservationService.myReservationPrint();
    }

    // 구장 예약 유효성 검사
    @GetMapping("/effectiveness")
    public boolean effectiveness(int bno ){
        return reservationService.effectiveness(bno);

    }// effectiveness end

}
