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






    // 게시판 수정
    @PutMapping("/update")
    public boolean bUpdate(@RequestBody Map<String, String> map){
        System.out.println("BoardController.bUpdate");
        System.out.println("map = " + map);
        return boardService.bUpdate(map);
    }   // bUpdate() end


    // 게시물 삭제
    @DeleteMapping("/")
    public boolean Bdelete(){}


}
