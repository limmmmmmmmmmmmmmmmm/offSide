package web.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private int bno;
    private String btitle;
    private String baddress;
    private String bdatetime;
    private int bprice;
    private String bfile1;
    private String bfile2 ;


}
