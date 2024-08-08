package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.MemberDto;
import web.service.ManagerService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class ManagerController {
    @Autowired ManagerService managerService;


    // 가입된 회원 전체 출력
    @GetMapping("/mprint")
    public List<MemberDto> mPrint(){
        System.out.println("ManagerController.mPrint");
        System.out.println("managerService = " + managerService);
        return managerService.mPrint();
    }
}
