package web.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PointlogDto {

    private int pno;                //포인트 로그 코드
    private int pindecrease;        // 포인트 증감 로그
    private String preason;         //포인트 증감 사유
    private int pstate;             // 포인트 상태
    private int mno;                //회원 번호
    private String accountlog;      // 계좌로그
    private String papprovedate;    //포인트 승인 날짜 시간
    private String pregistration;   //레코드 등록 날짜 시간


}
