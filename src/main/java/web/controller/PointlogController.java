package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.PointlogService;

@RestController
@RequestMapping("/point")
public class PointlogController {
    @Autowired PointlogService pointlogService;


    // 회원가입시 1만포인트 지급
    @GetMapping("/pay")
    public void pointPay(){

    }




}
