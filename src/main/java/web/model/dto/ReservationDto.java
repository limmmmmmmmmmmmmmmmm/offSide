package web.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private int rno;        // 예약번호
    private int rstate;     // 예약 확인 상태
    private int mno;        // 회원번호
    private int bno;        // 구장번호
    private String rdate;   // 레코드 등록 날짜/시간 (예약 날짜/시간)

}
