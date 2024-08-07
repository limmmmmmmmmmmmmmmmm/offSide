package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import web.model.dto.BoardDto;

import java.sql.PreparedStatement;

@Component
public class BoardDao extends Dao{

    //게시물 등록
    public boolean bwrite(BoardDto boardDto){
        try {
            String sql = "insert into board (btitle, baddress, bdatetime, bprice) values (? , ? , ?, ?) ;";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, boardDto.getBtitle());
            ps.setString(2, boardDto.getBaddress());
            ps.setString(3, boardDto.getBdatetime());
            ps.setInt(4,boardDto.getBprice());
            int count = ps.executeUpdate();
            if (count==1){return true;} //값을 가져오면 true 반환

        }catch (Exception e){
            System.out.println("e = " + e);
        }
        return false; //값이 없으면 false 반환
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
