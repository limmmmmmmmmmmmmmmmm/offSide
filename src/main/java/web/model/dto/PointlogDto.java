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


}
