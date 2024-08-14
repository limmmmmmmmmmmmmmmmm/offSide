package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.PointlogDto;
import web.service.PointlogService;

@RestController
@RequestMapping("/point")
public class PointlogController {
    @Autowired PointlogService pointlogService;


    // 회원가입시 1만포인트 지급
    @GetMapping("/pay")
    public void pointPay(){

    }

    // 포인트 충전
    @PostMapping("/charge")
    public boolean pointCharge(@RequestBody PointlogDto pointlogDto) {
        System.out.println("PointlogController.pointCharge");
        System.out.println("pointlogDto = " + pointlogDto);

        return pointlogService.pointCharge(pointlogDto);
    }





}
