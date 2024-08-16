package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dto.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import web.model.dto.PointlogDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import web.model.dto.PointlogDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import web.model.dto.BoardDto;
import web.model.dto.PointlogDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class PointlogDao extends Dao {

    // 회원가입시 1만포인트 지급

    public boolean pointPay(PointlogDto pointlogDto){
        try {
            String sql = "insert into pointLog(pindecrease, preason, pstate, mno , accountlog, papprovedate )values(?,?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1 , pointlogDto.getPindecrease());
            ps.setString(2 , pointlogDto.getPreason());
            ps.setInt(3 , pointlogDto.getPstate());
            ps.setInt(4 , pointlogDto.getMno());
            ps.setString(5 , pointlogDto.getAccountlog());
            ps.setString(6, pointlogDto.getPapprovedate());
            int count = ps.executeUpdate();
            if (count == 1 ) return true;
        }catch (Exception e){ System.out.println(e);
        }return false;
    }   // method end

    //회원 포인트 내역 출력
    public List<PointlogDto> mypointPrint(int loginNo){
        System.out.println("PointlogDao.mypointPrint");
        System.out.println("loginNo = " + loginNo);
        List<PointlogDto> list = new ArrayList<>();
        try {
            String sql= "SELECT * \n" +
                    " FROM pointLog \n" +
                    " WHERE mno = ?\n" +
                    "  AND preason LIKE '%포인트충전%';";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setInt(1,loginNo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                PointlogDto pointlogDto = PointlogDto.builder()
                        .mno(rs.getInt("mno"))
                        .preason(rs.getString("preason"))
                        .pregistration(rs.getString("pregistration"))   //레코드 등록 날짜
                        .pindecrease(rs.getInt("pindecrease"))
                        .pstate(rs.getInt("pstate"))
                        .build();
                System.out.println(pointlogDto);
                list.add(pointlogDto);
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return list;
    }





    // 충전하는 회원들 리스트 출력
    public List<PointlogDto> apaylist(){
        ArrayList<PointlogDto> list = new ArrayList<>();
        try {
            System.out.println("포인트 다오");
            String sql = "SELECT m.mid, m.mname, p.pindecrease, p.preason, p.accountlog, p.papprovedate, p.pregistration\n" +
                    "FROM pointLog p\n" +
                    "JOIN member m\n" +
                    "ON p.mno = m.mno\n" +
                    "where p.preason = '포인트충전';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                // 다른 Dto 필드값을 넣을때 Map 사용 또는 Dto에 추가하기
                PointlogDto pointlogDto = PointlogDto.builder()
                        .mid(rs.getString("mid"))
                        .mname(rs.getString("mname"))
                        .pindecrease(rs.getInt("pindecrease"))
                        .preason(rs.getString("preason"))
                        .accountlog(rs.getString("accountlog"))
                        .papprovedate(rs.getString("papprovedate"))
                        .pregistration(rs.getString("pregistration"))
                        .build();
                System.out.println("pointlogDto = " + pointlogDto);
                list.add(pointlogDto);
                System.out.println(list);
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        System.out.println("포인트 다오");
        return list;

    }
    // 버튼 승인
    public boolean payAgree(){
        try {
            String sql = "";

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    //포인트 누적 출력
     public int pointAdd(int loginNo){
        try {
            String sql= "select mno, sum(pindecrease) from pointLog where pstate= 1 and mno=? group by mno;";
            PreparedStatement ps=conn.prepareStatement(sql);
            ps.setInt(1,loginNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return rs.getInt("sum(pindecrease)");
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return 0;
     }
}






