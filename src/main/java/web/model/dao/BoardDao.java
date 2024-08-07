package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.sql.PreparedStatement;

@Component
public class BoardDao extends Dao{








    // 게시물 삭제
    public boolean bDelete(int bno){
        System.out.println("BoardDao.bDelete");
        System.out.println("bno = " + bno);
        try {
            String sql = "delete from board where bno = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1 , bno);

            int count = ps.executeUpdate();
            if (count == 1) return true;

        }catch (Exception e){ System.out.println(e); }
        return false;
    } // Bdelete end
}   // BoardDao end
