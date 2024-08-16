package web.model.dao;

import org.springframework.stereotype.Component;

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

}
