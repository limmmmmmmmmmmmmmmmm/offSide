package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import web.model.dao.BoardDao;

@Service
public class BoardService {
    @Autowired
    BoardDao boardDao;









    // 게시물 삭제
    public boolean bDelete(int bno){
        System.out.println("BoardService.bDelete");
        System.out.println("bno = " + bno);
        return boardDao.bDelete(bno);
    }
}
