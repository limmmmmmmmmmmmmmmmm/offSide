package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.BoardService;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;






    // 게시물 삭제
    @DeleteMapping("/")
    public boolean Bdelete(){}


}
