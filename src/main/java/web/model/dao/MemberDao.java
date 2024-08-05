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

    //  아이디 중복 검사
    public boolean mIdCheck( String id ){  System.out.println("MemberDao.mIdCheck"); System.out.println("id = " + id);

        try{ String sql = "select id from member where binary(id) = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString( 1 , id );
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){ return true; }
        }catch (Exception e ){ System.out.println(e); }
        return false;
    }


    //마이페이지 정보
    public MemberDto mMyInfo(int loginMno) {//MemberDto 타입의 mMyInfo라는 이름의 공용 멤버 변수를 클래스 내에서 선언
        System.out.println("MemberDao.mMyInfo"); //확인
        System.out.println("conn = " + conn); //확인
        try {//예외 처리
            String sql = "select * from member where mno = ?"; //회원번호를 찾아 회원의 상세정보를 출력시키는 sql 문
            PreparedStatement ps =conn.prepareStatement(sql); //주어진 SQL 쿼리(sql)를 준비하는 PreparedStatement 객체(ps)를 생성
            //로그인회원번호 추가
            //ps.setInt(1, 로그인회원번호 추가);
            ps.setInt(1, loginMno);

            ResultSet rs =ps.executeQuery(); // PreparedStatement를 사용하여 실행한 쿼리의 결과를 담고 있는 ResultSet 객체를 얻음

            if (rs.next()){ //상세 정보는 값이 1개이기 때문에 if 문을 써 결과를 담고 있는 rs 변수에 값이 들어왔는지 확인
                return MemberDto.builder()
                        .mno(rs.getInt("mno")) //회원 번호 값 확인
                        .mpw(rs.getString("mpw")) // 비밀번호 값 확인
                        .mid(rs.getString("mid")) //아이디 값 확인
                        .mname(rs.getString("mname")) // 회원이름 확인
                        .mphone(rs.getString("mphone")) // 연락처 확인
                        .mgender(rs.getString("mgender")) //성별 확인
                        .mbirth(rs.getString("mbirth")) //생일 확인
                        .maccount(rs.getString("maccount")) //환불계좌 확인
                        .build();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null; //rs 에 값이 담기지 않았을 경우 null값으로 표시
    }

    //로그인 함수
    public int mLogin( MemberDto memberDto ){
        System.out.println("MemberDao.mLogin");
        System.out.println("memberDto = " + memberDto);
        try{String sql = "select * from member where mid = ? and mpw =?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString( 1 , memberDto.getMid() );
            ps.setString( 2 , memberDto.getMpw() );
            ResultSet rs = ps.executeQuery();
            if( rs.next() ){ return rs.getInt("mno"); }
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
            ps.setInt(5, Integer.parseInt(mUpdateMap.get("mno")));
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



