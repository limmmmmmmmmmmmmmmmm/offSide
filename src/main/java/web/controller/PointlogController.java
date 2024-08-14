package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.PointlogDto;
import web.model.dto.PointlogDto;
import org.springframework.web.bind.annotation.*;
import web.model.dto.PointlogDto;
import web.service.PointlogService;

import java.util.List;

@RestController
@RequestMapping("/point")
public class PointlogController {
    @Autowired PointlogService pointlogService;



    // 충전하는 회원들 리스트 출력
    @GetMapping("/paylist")
    public List<PointlogDto> apaylist(){
        System.out.println("포인트 컨트롤");
       return pointlogService.apaylist();
    }

    // 포인트 충전
    @PostMapping("/charge")
    public boolean pointCharge(@RequestBody PointlogDto pointlogDto) {
        System.out.println("PointlogController.pointCharge");
        System.out.println("pointlogDto = " + pointlogDto);

        return pointlogService.pointCharge(pointlogDto);
    }



//    @PostMapping
//    public boolean payAgree(){
//        return PointlogService.payAgree()
//    }


}
