package web.model.dao;

import org.springframework.stereotype.Component;
import web.model.dto.BoardDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class BoardDao extends Dao{

    // 구장 목록 출력
    public List<BoardDto> bPrint() {
        System.out.println("BoardDao.bPrint");
        ArrayList<BoardDto> list = new ArrayList<>();
        try {
            String sql = "select * from board;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BoardDto boardDto = BoardDto.builder()
                        .bno(rs.getInt("bno"))
                        .btitle(rs.getString("btitle"))
                        .baddress(rs.getString("baddress"))
                        .bdatetime(rs.getString("bdatetime"))
                        .bprice(rs.getInt("bprice"))
                        .bfile1(rs.getString("bfile1"))
                        .bfile2(rs.getString("bfile2"))
                        .build();
                list.add(boardDto);
            }
        } catch (Exception e) {
            System.out.println("BoardDao -> bPrint -> e = " + e);
        }
        return list;
    }   // bPrint() end








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
