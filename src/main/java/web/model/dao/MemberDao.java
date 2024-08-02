package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
@Component
public class MemberDao extends Dao
{


    //마이페이지 정보
    public MemberDto mMyInfo() {
        try {
            String sql = "select * from member where mno = 1 ";
          PreparedStatement ps =conn.prepareStatement(sql);
            //로그인회원번호 추가
            //ps.setInt(1,memberDto.getMno() );
          ResultSet rs =ps.executeQuery();

          if (rs.next()){
              return MemberDto.builder()
                      .mid(rs.getString("mid"))
                      .mname(rs.getString("mname"))
                      .mphone(rs.getString("mphone"))
                      .mgender(rs.getString("mgender"))
                      .mbirth(rs.getString("mbirth"))
                      .maccount(rs.getString("macount"))
                      .build();
          }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
