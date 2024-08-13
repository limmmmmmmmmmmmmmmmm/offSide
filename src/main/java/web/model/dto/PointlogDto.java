package web.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PointlogDto {

    private int rno;
    private int rstate;  // 예약확인상태
    private int mno;        // member no
    private int bno;        // board no
    private String rdate;   // 날짜
}
