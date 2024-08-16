package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import web.model.dao.PointlogDao;
import web.model.dto.MemberDto;
import web.model.dto.PointlogDto;

import java.util.List;

@Service
public class PointlogService {
    @Autowired PointlogDao pointlogDao;

    // 회원가입시 1만포인트 지급
    public boolean pointPay(PointlogDto pointlogDto){
        return pointlogDao.pointPay(pointlogDto);

    }

    // 포인트 충전 신청
//    public boolean pointCharge(PointlogDto pointlogDto) {
//        pointlogDto.setPreason("포인트충전");
//        System.out.println("PointlogService.pointCharge");
//        System.out.println("pointlogDto = " + pointlogDto);
//
//        return pointlogDao.pointPay(pointlogDto);
//    }

    // 포인트 환불 신청
    public boolean pointRefund(@RequestBody PointlogDto pointlogDto) {
        pointlogDto.setPreason("포인트환불");
        System.out.println("PointlogService.pointRefund");
        System.out.println("pointlogDto = " + pointlogDto);

        return pointlogDao.pointPay(pointlogDto);
    }



    // 충전하는 회원들 리스트 출력
    public List<PointlogDto> apaylist(){
        System.out.println("포인트 서비스");
        return pointlogDao.apaylist();
    }

    // 버튼 누르면 true 바꿔주기
    public boolean payAgree(PointlogDto pointlogDto){
        System.out.println("포인트 승인 서비스");
        System.out.println("pointlogDto = " + pointlogDto);

        // 포인트 로그상태 알기
        PointlogDto point = pointlogDao.getPointLog( pointlogDto.getPno() );
        //
        int pstate = point.getPstate();
        //
        pointlogDto.setPstate( pstate == 1 ? 0 : 1 );

        System.out.println(">>>>>>pointlogDto = " + pointlogDto);

        return pointlogDao.payAgree(pointlogDto);
    }

    //포인트 누적 출력
    public PointlogDto pointAdd(){
        return pointlogDao.pointAdd();
    }

    // 포인트 충전
    public boolean pointCharge(PointlogDto pointlogDto) {
        pointlogDto.setPreason("포인트충전");
        System.out.println("PointlogService.pointCharge");
        System.out.println("pointlogDto = " + pointlogDto);


        return false;
    }





}
