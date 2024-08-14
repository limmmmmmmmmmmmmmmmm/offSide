package web.model.dao;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.dto.PointlogDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component
public class PointlogDao extends Dao {

    // 회원가입시 1만포인트 지급

    public void pointPay(){
        try {

        }catch (Exception e){ System.out.println(e);
        }
    }   // method end



    // 충전하는 회원들 리스트 출력
    public List<PointlogDto> apaylist(){
        ArrayList<PointlogDto> list = new ArrayList<>();
        try {
            System.out.println("포인트 다오");
            String sql = "SELECT m.mid, m.mname, p.pindecrease, p.preason, p.accountlog, p.papprovedate, p.pregistration\n" +
                    "FROM pointLog p\n" +
                    "JOIN member m\n" +
                    "ON p.mno = m.mno\n" +
                    "where p.preason = '포인트충전';";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                // 다른 Dto 필드값을 넣을때 Map 사용 또는 Dto에 추가하기
                PointlogDto pointlogDto = PointlogDto.builder()
                        .mid(rs.getString("mid"))
                        .mname(rs.getString("mname"))
                        .pindecrease(rs.getInt("pindecrease"))
                        .preason(rs.getString("preason"))
                        .accountlog(rs.getString("accountlog"))
                        .papprovedate(rs.getString("papprovedate"))
                        .pregistration(rs.getString("pregistration"))
                        .build();
                System.out.println("pointlogDto = " + pointlogDto);
                list.add(pointlogDto);
                System.out.println(list);
            }
        }catch (Exception e){
            System.out.println("e = " + e);
        }
        System.out.println("포인트 다오");
        return list;

    }
    // 버튼 승인
    public boolean payAgree(){
        try {
            String sql = "";

        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

}
