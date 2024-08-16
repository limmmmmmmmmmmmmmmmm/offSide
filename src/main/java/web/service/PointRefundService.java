package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import web.model.dto.PointlogDto;

@Service
public class PointRefundService {
    @Autowired
    PointlogService pointlogService;

    // 포인트 환불 신청
    public boolean pointRefund(@RequestBody PointlogDto pointlogDto) {
        pointlogDto.setPreason("포인트환불");
        System.out.println("PointlogService.pointRefund");
        System.out.println("pointlogDto = " + pointlogDto);

        return pointlogService.pointPay(pointlogDto);
    }   // pointRefund() end

}   // class end
