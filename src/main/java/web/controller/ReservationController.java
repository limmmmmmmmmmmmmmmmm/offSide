package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.ReservationService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired ReservationService reservationService;

    // 내가 예약한 구장 목록 요청
    @GetMapping("/my/print")
    public List<Map<String, String>> myReservationPrint() {
        System.out.println("ReservationController.reservationListPrint");

        return reservationService.myReservationPrint();
    }


}
