package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dao.PointlogDao;
import web.model.dto.PointlogDto;

@Service
public class PointlogService {
    @Autowired PointlogDao pointlogDao;


    // 회원가입시 1만포인트 지급
    public void pointPay(PointlogDto pointlogDto){

    }

    // 포인트 충전
    public boolean pointCharge(PointlogDto pointlogDto) {
        pointlogDto.setPreason("포인트충전");
        System.out.println("PointlogService.pointCharge");
        System.out.println("pointlogDto = " + pointlogDto);


        return false;
    }





}
