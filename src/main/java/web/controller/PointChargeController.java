package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.PointlogDto;
import web.service.PointChargeService;

@RestController
@RequestMapping("/point")
public class PointChargeController {
    @Autowired PointChargeService pointChargeService;

    // 포인트 충전 신청
    @PostMapping("/charge")
    public boolean pointCharge(@RequestBody PointlogDto pointlogDto) {
        System.out.println("PointlogController.pointCharge");
        System.out.println("pointlogDto = " + pointlogDto);

        return pointChargeService.pointCharge(pointlogDto);
    }   // pointCharge() end
}
