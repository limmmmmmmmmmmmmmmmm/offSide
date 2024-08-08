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

    // 구장 목록 출력
    public List<BoardDto> bPrint() {
        System.out.println("BoardService.bPrint");
        return boardDao.bPrint();
    }

    //게시물 등록
    public boolean bwrite(BoardDto boardDto){
        return  boardDao.bwrite(boardDto);
    }


    //===================================== 6. 글 수정
//    public boolean bUpdate(Map<String, String> map){
//        System.out.println("BoardService.bUpdate");
//        System.out.println("map = " + map);
//        return boardDao.bUpdate(map); // 로그인 번호는 필요없으니 바로 Dao 전달
//    }   // bUpdate() end

    // 수정할 게시물 출력
    public BoardDto bRead(int bno){
        return boardDao.bRead(bno);
    }
    //===================================== 6. 글 수정


    // 게시물 삭제
    public boolean bDelete(int bno){
        return boardDao.bDelete(bno);
    }



}
