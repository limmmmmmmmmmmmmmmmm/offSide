package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;

import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;

    // 구장 목록 출력
    public List<BoardDto> bPrint() {
        System.out.println("BoardService.bPrint");
        return boardDao.bPrint();
    }









    // 게시물 삭제
    public boolean bDelete(int bno){
        return boardDao.bDelete(bno);
    }
}
