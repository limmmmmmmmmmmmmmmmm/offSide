package web.model.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class ManagerDao extends Dao{

    // 가입된 회원 전체 출력
    public List<MemberDto> mPrint(){
        System.out.println("ManagerDao.mPrint");
        System.out.println("conn = " + conn);
        List<MemberDto> list = new ArrayList<>();
        try {
            String sql = "select * from member";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                MemberDto memberDto = MemberDto.builder()
                        .mid(rs.getString("mid"))
                        .mname(rs.getString("mname"))
                        .mphone(rs.getString("mphone"))
                        .mgender(rs.getString("mgender"))
                        .mbirth(rs.getString("mbirth"))
                        .maccount(rs.getString("maccount"))
                        .build();
                list.add(memberDto);
            }
        }catch (Exception e){ System.out.println(e);
        }
        return list;
    }   // mPrint end



}
