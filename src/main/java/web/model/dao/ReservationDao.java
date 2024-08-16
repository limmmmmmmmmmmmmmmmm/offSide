package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReservationDao extends Dao {

    // 내가 예약한 구장 목록 요청
    public List<Map<String, String>> myReservationPrint(int loginMno) {
        System.out.println("ReservationDao.reservationListPrint");
        System.out.println("loginMno = " + loginMno);

        List<Map<String, String>> list = new ArrayList<>();
        try {
            String sql = "select * from reservation r inner join board b on r.bno = b.bno where mno = ? order by bno desc;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, loginMno);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, String> map = new HashMap<>();
                map.put("rno", String.valueOf(rs.getInt("rno")));
                map.put("rstate", String.valueOf(rs.getInt("rstate")));
                map.put("mno", String.valueOf(rs.getInt("mno")));
                map.put("bno", String.valueOf(rs.getInt("bno")));
                map.put("rdate", rs.getString("rdate"));
                map.put("btitle", rs.getString("btitle"));
                map.put("baddress", rs.getString("baddress"));
                map.put("bdatetime", rs.getString("bdatetime"));
                map.put("bprice", String.valueOf(rs.getInt("bprice")));
                map.put("bfile1", rs.getString("bfile1"));
                map.put("bfile2", rs.getString("bfile2"));
                list.add(map);
            }
        } catch (Exception e) {
            System.out.println("ReservationDao -> reservationListPrint -> e = " + e);
        }
        return list;
    }
    // 구장 예약
    public boolean stadiumReservation( int rstate , int mno , int bno ) {

        try {
            String sql = "insert into reservation(rstate , mno , bno) values(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1 , rstate);
            ps.setInt(2 , mno);
            ps.setInt(3 , bno);

            int count = ps.executeUpdate();
            if (count == 1 ) return true;

        }catch (Exception e){ System.out.println(e);
        }return false;

    }

    // 구장 예약 취소
    public boolean StadiumCancellation( int rstate , int bno , int mno ){
        try {
            String sql = "update reservation set rstate = ? where bno = ? and mno = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1 , rstate);
            ps.setInt(2 , bno);
            ps.setInt(3 , mno);
            int count = ps.executeUpdate();
            if (count == 1)return true;

        }catch (Exception e){ System.out.println(e);
        }return false;
    }   // StadiumCancellation

    // 구장 예약 유효성 검사
    public boolean effectiveness(int bno , int mno , int rstate){
        try {
            String sql = "select * from reservation where bno = ? and mno = ? and rstate = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1 , bno);
            ps.setInt(2 , mno);
            ps.setInt(3 , rstate);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return true;
            }

        }catch (Exception e){ System.out.println(e);}
        return false;
    }// effectiveness end



}   // ReservationDao end