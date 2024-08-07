package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.service.BoardService;

import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;








    // 게시물 삭제
    @DeleteMapping("/delete")
    public boolean bDelete(int bno){
        System.out.println("BoardController.bDelete");
        System.out.println("bno = " + bno);
        return boardService.bDelete(bno);
    }


}
