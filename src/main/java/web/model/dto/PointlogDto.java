package web.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PointlogDto {

    private int pno;            // 포인트 로그 코드
    private int pindecrease;    // 포인트 증감
    private String preason;     // 증감사유
    private int pstate;         // 포인트 상태
    private int mno;            // mno
    private String accountlog;  // 계좌번호
    private String papprovedate;    //
    private String pregistration;

    // 신청한 내역들 출력할때 필요해서 memberDto에 mid 와 mname만
    // PointlogDto에 추가 하겠습니다.
    private String mid;
    private String mname;

}
