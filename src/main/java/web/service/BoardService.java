package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;

import java.util.List;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;

import java.util.Map;

@Service
public class BoardService {

    @Autowired MemberService memberService;
    @Autowired BoardDao boardDao;
    @Autowired FileService fileService;

    // 구장 목록 출력
    public List<BoardDto> bPrint() {
        System.out.println("BoardService.bPrint");
        return boardDao.bPrint();
    }

    //게시물 등록
    public boolean bwrite(BoardDto boardDto){
//        //회원의 로그인회원번호 구하기
//        //1. 로그인 세션에서 값 호출
//        Object object=memberService.loginCheck();
//        if (object ==null)return false; //비로그인시 함수 강제종료/취소
//        //2. 세션 내 회원번호 속성 호출
//        MemberDto memberDto=(MemberDto)object;
//        //3. 속성 호출
//        int loginNo=memberDto.getMno();
//        //4. BoardDto 에 담아주기
//        boardDto.setMno(loginNo);

        // - 파일 업로드 처리
        if( boardDto.getUploadFile().isEmpty() ){}// - 업로드 된 파일이 존재  하지 않으면
        else{ // 존재하면
            String uploadFileName= fileService.fileUpload(boardDto.getUploadFile());
            // 1. 만약에 업로드가 실패 했으면  글쓰기 실패
            if( uploadFileName == null ) return false;
            // 2. BoardDto 에 업로드 된 파일명 담아주기
            boardDto.setBfile1( uploadFileName );
        }
        if (boardDto.getUploadFile2().isEmpty()){}
        else{ // 존재하면
            String uploadFileName= fileService.fileUpload(boardDto.getUploadFile2());
            // 1. 만약에 업로드가 실패 했으면  글쓰기 실패
            if( uploadFileName == null ) return false;
            // 2. BoardDto 에 업로드 된 파일명 담아주기
            boardDto.setBfile2( uploadFileName );
        }

        return  boardDao.bwrite(boardDto);
    }


    //===================================== 6. 글 수정
    public boolean bUpdate(Map<String, String> map){
        System.out.println("서비스 수정");
        System.out.println("map = " + map);
        return boardDao.bUpdate(map); // 로그인 번호는 필요없으니 바로 Dao 전달
    }   // bUpdate() end

    // 수정할 게시물 출력
    public BoardDto bRead(int bno){
        System.out.println("bno2 = " + bno);
        return boardDao.bRead(bno);
    }
    //===================================== 6. 글 수정


    // 게시물 삭제
    public boolean bDelete(int bno){
        return boardDao.bDelete(bno);
    }



}
