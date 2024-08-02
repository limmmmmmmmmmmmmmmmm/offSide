package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.dao.MemberDao;
import web.model.dto.MemberDto;

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




}
