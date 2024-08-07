package web.model.dao;

import com.sun.jdi.event.ExceptionEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.sql.PreparedStatement;
import java.util.Map;

@Component
public class BoardDao extends Dao{






    // 게시물 수정
    public boolean bUpdate(Map<String, String> map) {
        System.out.println("BoardDao.bUpdate");
        System.out.println("map = " + map);
        try {
            String sql = "update board set btitle = ? , baddress = ? , bdatetime = ? , bprice=? , bfile1 = ? ,bfile2 =? where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1 , map.get("btitle"));
            ps.setString(2 , map.get("bcontent"));
            ps.setString(3 , map.get("bdatetime"));
            ps.setInt(4,Integer.parseInt(map.get("bprice")));
            ps.setString(5 , map.get("bfile1"));
            ps.setString(6 , map.get("bfile2"));
            int count = ps.executeUpdate();
            if(count==1){
                return true;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false;
    }



    // 게시물 삭제
    public boolean bDelete(int bno){
        try {
            String sql = "delete from member where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1 , bno);

            int count = ps.executeUpdate();
            if (count == 1) return true;

        }catch (Exception e){ System.out.println(e); }
        return false;
    } // Bdelete end
}   // BoardDao end
