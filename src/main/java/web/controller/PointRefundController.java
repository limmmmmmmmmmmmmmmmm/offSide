package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.PointlogDto;
import web.service.PointRefundService;

@RestController
@RequestMapping("/point")
public class PointRefundController {
    @Autowired PointRefundService pointRefundService;

    // 포인트 환불 신청
    @PostMapping("/refund")
    public boolean pointRefund(@RequestBody PointlogDto pointlogDto) {
        System.out.println("PointlogController.pointRefund");
        System.out.println("pointlogDto = " + pointlogDto);

        return pointRefundService.pointRefund(pointlogDto);
    }   // pointRefund() end
}
