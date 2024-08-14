package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dao.PointlogDao;
import web.model.dto.PointlogDto;

import java.util.List;

@Service
public class PointlogService {
    @Autowired PointlogDao pointlogDao;


    // 회원가입시 1만포인트 지급
    public void pointPay(PointlogDto pointlogDto){

    }

    // 충전하는 회원들 리스트 출력
    public List<PointlogDto> apaylist(){
        System.out.println("포인트 서비스");
        return pointlogDao.apaylist();
    }
    // 버튼 누르면 true 바꿔주기
    public boolean payAgree(){
        return PointlogDao.payAgree()
    }


}
