package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;



import org.springframework.web.bind.annotation.PostMapping;

@Service
public class MemberService {
    @Autowired
    MemberDao memberDao;


    // [1] 회원가입
    public boolean OffSidSignup(MemberDto memberDto){
        System.out.println("MemberService.OffSidSignup");
        System.out.println("memberDao = " + memberDao);
        return memberDao.OffSidSignup(memberDto);
    }




public MemberDto mMyInfo(){
    System.out.println("MemberService.mMyInfo");
    return memberDao.mMyInfo();
}

}
