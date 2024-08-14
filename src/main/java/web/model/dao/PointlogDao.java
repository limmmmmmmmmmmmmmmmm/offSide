package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dto.BoardDto;
import web.model.dto.PointlogDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Component
public class PointlogDao extends Dao {

    // 회원가입시 1만포인트 지급

    public void pointPay(){
        try {

        }catch (Exception e){ System.out.println(e);
        }
    }   // method end

    //회원 포인트 내역 출력
    public PointlogDto mypointPrint(int loginNo){
        try {
            String sql= "select *from pointLog where mno= ?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setInt(1,loginNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PointlogDto pointlogDto = PointlogDto.builder()
                        .preason(rs.getString("preason"))
                        .pregistration(rs.getString("pregistration"))
                        .build();
                System.out.println(pointlogDto);
                return pointlogDto;
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
    return null;
    }




}
