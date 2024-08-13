package web.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class PointDto {

    private int rno;
    private String rstate;
    private int mno;
    private int bno;
    private String rdate;
}
