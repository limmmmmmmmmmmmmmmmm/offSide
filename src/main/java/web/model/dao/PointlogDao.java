package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
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



}
