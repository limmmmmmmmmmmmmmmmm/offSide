package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.BoardDto;
import web.service.BoardService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    // 구장 목록 출력
    @GetMapping("/list")
    public List<BoardDto> bPrint() {
        System.out.println("BoardController.bPrint");
        return boardService.bPrint();
    }






//    // 게시판 수정
//    @PutMapping("/update")
//    public boolean bUpdate(@RequestBody Map<String, String> map){
//        System.out.println("BoardController.bUpdate");
//        System.out.println("map = " + map);
//        return boardService.bUpdate(map);
//    }   // bUpdate() end


    // 게시물 삭제
    @DeleteMapping("/delete")
    public boolean bDelete(int bno){
        return boardService.bDelete(bno);
    }


}
