package web.model.dto;

import lombok.*;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BoardDto {

    private int bno;            // 구장 번호
    private String btitle;      // 제목/구장명
    private String baddress;    // 주소
    private String bdatetime;   // 경기날짜시간
    private int bprice;         // 가격
    private String bfile1;      // 파일1
    private String bfile2 ;     // 파일2

}
