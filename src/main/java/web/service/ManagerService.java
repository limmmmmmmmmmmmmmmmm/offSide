package web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dao.ManagerDao;
import web.model.dto.MemberDto;

import java.util.List;

@Service
public class ManagerService {

    @Autowired ManagerDao managerDao;


    // 가입된 회원 전체 출력
    public List<MemberDto> mPrint(){
        System.out.println("ManagerService.mPrint");
        System.out.println("managerDao = " + managerDao);
        return managerDao.mPrint();
    }


}
