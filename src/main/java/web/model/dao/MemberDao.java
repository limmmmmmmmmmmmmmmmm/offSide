package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;

@Component
public class MemberDao extends Dao{


    // [1] 회원가입
    public boolean OffSidSignup(MemberDto memberDto){
        System.out.println("MemberDao.OffSidSignup");
        System.out.println("memberDto = " + memberDto);
        try {
            String sql = "insert into member( mid , mpw , mname, mphone, mgender, mbirth, maccount  ) values (?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString( 1 , memberDto.getMid());
            ps.setString( 2 , memberDto.getMpw());
            ps.setString( 3 , memberDto.getMname());
            ps.setString( 4 , memberDto.getMphone());
            ps.setString( 5 , memberDto.getMgender());
            ps.setString( 6 , memberDto.getMbirth());
            ps.setString( 7 , memberDto.getMaccount());

            int count = ps.executeUpdate();
            if (count == 1 )return true;
        }catch (Exception e){System.out.println(e);
        }
        return false;
    } // OffSidSignup end


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


}// MemberDao end



