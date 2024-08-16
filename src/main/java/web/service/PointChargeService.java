package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dto.PointlogDto;

@Service
public class PointChargeService {
    @Autowired PointlogService pointlogService;

    // 포인트 충전 신청
    public boolean pointCharge(PointlogDto pointlogDto) {
        pointlogDto.setPreason("포인트충전");
        System.out.println("PointlogService.pointCharge");
        System.out.println("pointlogDto = " + pointlogDto);

        return pointlogService.pointPay(pointlogDto);
    }   // pointCharge() end

}   // class end
