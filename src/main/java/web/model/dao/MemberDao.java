package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.MemberDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

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
            String sql = "select * from member where mno = 1";
            PreparedStatement ps =conn.prepareStatement(sql);
            //로그인회원번호 추가
            //ps.setInt(1, 로그인회원번호 추가);

            ResultSet rs =ps.executeQuery();

            if (rs.next()){
                return MemberDto.builder()
                        .mno(rs.getInt("mno"))
                        .mid(rs.getString("mid"))
                        .mpw(rs.getString("mpw"))
                        .mname(rs.getString("mname"))
                        .mphone(rs.getString("mphone"))
                        .mgender(rs.getString("mgender"))
                        .mbirth(rs.getString("mbirth"))
                        .maccount(rs.getString("maccount"))
                        .build();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    //로그인 함수
    public int mLogin( MemberDto memberDto ){
        System.out.println("MemberDao.mLogin");
        System.out.println("memberDto = " + memberDto);
        try{String sql = "select * from member where id = ? and pw =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString( 1 , memberDto.getMid() );
            ps.setString( 2 , memberDto.getMpw() );
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){ return rs.getInt("no"); }
        }catch (Exception e ){ System.out.println(e);   }
        return 0; // 0 은 회원번호가 없다 뜻
    }



    // 회원 수정 함수     { mpw : mpw , newpw: newpw , mname : mname , mphone : mphone , maccount : maccount }
    public boolean mUpdate(Map<String, String> mUpdateMap) {
        System.out.println("MemberDao.mUpdate");
        System.out.println("mUpdateMap = " + mUpdateMap);

        try {
            String sql = "update member set mpw = ?, mname = ?, mphone = ?, maccount = ? where mno = ? and mpw = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mUpdateMap.get("newpw"));
            ps.setString(2, mUpdateMap.get("mname"));
            ps.setString(3, mUpdateMap.get("mphone"));
            ps.setString(4, mUpdateMap.get("maccount"));
            ps.setInt(5, Integer.parseInt(mUpdateMap.get("mno")));     // 여기 나중에 수정해야 할지도
            ps.setString(6, mUpdateMap.get("mpw"));

            int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("MemberDao -> mUpdate -> e = " + e);
        }
        return false;
    }   // mUpdate() end

    // 회원 탈퇴 함수
    public boolean mDelete(int loginMno, String mpw) {
        System.out.println("MemberDao.mDelete");
        System.out.println("loginMno = " + loginMno + ", mpw = " + mpw);
        try {
            String sql = "delete from member where mno = ? and mpw = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, loginMno);
            ps.setString(2, mpw);
            int count = ps.executeUpdate();
            if (count == 1) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("MemberDao -> mDelete -> e = " + e);
        }
        return false;
    }   // mDelete() end

}// MemberDao end



