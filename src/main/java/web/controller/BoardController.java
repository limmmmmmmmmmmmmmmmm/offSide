package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.model.dto.BoardDto;
import web.model.dto.MemberDto;
import web.service.BoardService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired BoardService boardService;

    // 구장 목록 출력
    @GetMapping("/list")
    // HTTP 매개변수 대소문자 구분시 @RequestParam 사용하여 명시하기.
    public List<BoardDto> bPrint( @RequestParam("searchKey") String searchKey, @RequestParam("searchKeyword") String searchKeyword) {
        System.out.println("BoardController.bPrint");
        System.out.println("searchKey = " + searchKey + ", searchKeyword = " + searchKeyword);
        return boardService.bPrint(searchKey , searchKeyword);
    }


    //게시물 등록
    @PostMapping("/write")
    public boolean bwrite(BoardDto boardDto){
        return boardService.bwrite(boardDto);
    }


    // =============================게시판 수정
    //수정할 게시판 출력
    @GetMapping("/bread")
    public BoardDto bRead(int bno){ // 수정할 게시물정보 가져올려면 bno
        return boardService.bRead(bno);
    }

    // 게시판 수정
        // 각자
    @PutMapping("/update")
    public boolean bUpdate(@RequestBody Map <String, String> map){
        System.out.println("컨트롤 수정");
        System.out.println("map = " + map);
        return boardService.bUpdate(map); // boardService 전달
    }   // bUpdate() end
    // =============================게시판 수정


    // 게시물 삭제
    @DeleteMapping("/delete")
    public boolean bDelete(int bno){
        System.out.println("BoardController.bDelete");
        System.out.println("bno = " + bno);
        return boardService.bDelete(bno);
    }


}
