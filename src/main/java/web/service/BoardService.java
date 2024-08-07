package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import web.model.dao.BoardDao;
import web.model.dto.BoardDto;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;



    //게시물 등록
    public boolean bwrite(BoardDto boardDto){
        return  boardDao.bwrite(boardDto);
    }





    // 게시물 삭제
    public boolean bDelete(int bno){
        return boardDao.bDelete(bno);
    }
}
