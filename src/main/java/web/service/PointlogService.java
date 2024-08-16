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


    // (통합) 포인트 로그 서비스 , 1.회원가입 포인트 충ㄹ전 , 2.포인트 충전 신청 , 3.포인트 환불 신청
    public boolean pointPay(PointlogDto pointlogDto){
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








}
