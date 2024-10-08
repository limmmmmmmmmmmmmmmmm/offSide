package web.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    //업로드시 바이트를 저장하고 있는 필드1
    private MultipartFile uploadFile1;// 첨부파일

    //업로드시 바이트를 저장하고 있는 필드2
    private MultipartFile uploadFile2;// 첨부파일

    // + 검색 필드
    private String searchKey;   // 8. 검색 조회시 사용되는 필드명
    private String searchKeyword; // 9. 검색 조회시 사용되는 필드의 값


}
