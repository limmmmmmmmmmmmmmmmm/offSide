package web.model.dto;


import lombok.*;

@Builder@Getter@Setter
@ToString@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private int mno;            //회원 번호
    private String mid;         //회원 ID
    private String mpw;         //회원 PW
    private String mname;       //회원 이름
    private String mphone;      //회원 연락처
    private String mgender;     //회원 성별
    private String mbirth;      //회원 생년원일
    private String maccount;    //회원 계좌번호
    //헤더에 포인트 금액을 알려주기 위해 추가함
    private int pindecrease;        // 포인트 증감 로그

}
