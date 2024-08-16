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


    // 충전하는 회원들 리스트 출력
    public List<PointlogDto> apaylist(){
        System.out.println("포인트 서비스");
        return pointlogDao.apaylist();
    }
    // 버튼 누르면 true 바꿔주기
//    public boolean payAgree(){
//        return PointlogDao.payAgree()
//    }

    //포인트 누적 출력
    public PointlogDto pointAdd(){
        return pointlogDao.pointAdd();
    }








}
