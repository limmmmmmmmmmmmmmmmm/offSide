package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.FileService;

@RestController
public class FileController {
    @Autowired
    private FileService fileServise;
    //1. 다운로드 요청 처리 , 매개변수 : 다운로드 를 받을 파일명을 요청으로 받아야 한다.

    @GetMapping("file/download")
    private void fileDownLoad(String filename){
        System.out.println("filename = " + filename);
        System.out.println("FileController.fileDownLoad");
        fileServise.fileDownLoad(filename);
    }


}
