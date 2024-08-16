package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class ReservationDao extends Dao {

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