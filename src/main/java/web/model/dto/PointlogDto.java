package web.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PointlogDto {

    private int pno;
    private int pindecrease;
    private String preason;
    private int pstate;
    private int mno;
    private String accountlog;
    private String papprovedate;
    private String pregistration;

    // 신청한 내역들 출력할때 필요해서 memberDto에 mid 와 mname만
    // PointlogDto에 추가 하겠습니다.
    private String mid;
    private String mname;

}
