package web.model.dto;


import lombok.*;

@Builder@Getter@Setter
@ToString@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private String mno;         //회원 번호
    private String mid;         //회원 ID
    private String mpw;         //회원 PW
    private String mname;       //회원 이름
    private String mphone;      //회원 연락처
    private String mgender;     //회원 성별
    private String mbirth;      //회원 생년원일
    private String maccount;    //회원 계좌번호

}
