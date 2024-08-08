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
    @Autowired BoardDao boardDao;
    @Autowired MemberService memberService;

    // 구장 목록 출력
    public List<BoardDto> bPrint() {
        System.out.println("BoardService.bPrint");
        return boardDao.bPrint();
    }

    //게시물 등록
    public boolean bwrite(BoardDto boardDto){
        return  boardDao.bwrite(boardDto);
    }


    // 6. 글 수정
    public boolean bUpdate(Map<String, String> map){
        System.out.println("BoardService.bUpdate");
        System.out.println("map = " + map);
        // 1. 로그인 세션에서 값 호출
        Object object = memberService.loginCheck();
        System.out.println("object = " + object);
        if (object == null) return false;
        MemberDto loginDto = (MemberDto) object;
        int loginMno = loginDto.getMno();
        map.put("no", String.valueOf(loginMno));
        return boardDao.bUpdate(map);
    }   // bUpdate() end


    // 게시물 삭제
    public boolean bDelete(int bno){
        return boardDao.bDelete(bno);
    }
}
