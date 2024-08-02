package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

@Service
public class MemberService {

@Autowired
    MemberDao memberDao;

public MemberDto mMyInfo(){
    System.out.println("MemberService.mMyInfo");
    return memberDao.mMyInfo();
}

}
