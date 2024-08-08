package web.model.dao;

import com.sun.jdi.event.ExceptionEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import web.model.dto.BoardDto;
import web.model.dto.BoardDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

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
