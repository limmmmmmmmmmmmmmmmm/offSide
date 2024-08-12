package web.service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileService {

    //[0]
    String uploadPath="C:\\Users\\tj-bu-703-14\\Desktop\\offSide\\build\\resources\\main\\static\\upload\\";
    //[1] 파일 업로드 : 매개변수로 파일의 바이트가 저장된 MultipartFile 인터페이스
    public String fileUpload(MultipartFile multipartFile){

        System.out.println(multipartFile.getContentType()); //파일의 확장자
        System.out.println(multipartFile.getName());//속성명
        System.out.println(multipartFile.getSize());//첨부파일의 바이트 사이즈/용량
        System.out.println(multipartFile.isEmpty());//첨부파일이 없으면 true, 있으면 false

        //1. 첨부파일의 실제 파일 이름 추출
        // + 클라이언트(유저)들이 서로 다른 파일내용의 같은 파일명으로 업로드 했을 때 식별이 불가능.
        // 해결방안 : 1. UUID( 고유성 보장하는 ID 규약 ) 2. 조합식별 설계(주로 업로드날짜/시간 와 파일명 조합)
        String uuid = UUID.randomUUID().toString(); //난수의 UUID 생성, 임의의 UUID 규약에 따른 문자열 생성
        System.out.println("uuid = " + uuid);
        String fileName=multipartFile.getOriginalFilename();
        //UUID +파일명 합치기 , uuid와 파일명 구분하는 문자조합 , 파일명의 _(언더바)가 존재하면 안된다.
        //추후에 _(언더바)기준으로 분리하면[0] UUID ,[1] 순수파일명
        //문자열.replaceAll("기존문자", "새로운문자"): 만약에 문자열내 기존문자가 존재하면 새로운 문자열로 치환해서 반환
        fileName=uuid + "_"+fileName.replaceAll("_","-"); //파일명에 "-"
        fileName= uuid +"_" + fileName;
        System.out.println("fileName = " + fileName);
        //2. 저장할 경로 만들기

        //3. 저장할 경로와 파일명 합치기
        String filePath=uploadPath + fileName;
        //4. 해당 경로로 설정한 file 객체 , transferTo (file 객체)
        File file=new File(filePath);
        //5.
        //multipartFile.transferTo(file 객체) :file 객체내 설정한 해당 경로로 파일 복사/저장/이동
        //일반예외 무조건 발생
        try {
            multipartFile.transferTo(file);
        }catch (Exception e){
            System.out.println("e = " + e);return  null;
        }
        return fileName;


    }

    @Autowired private HttpServletRequest request; //HTTP 요청 들어온 정보와 기능이 포함된 객체
    @Autowired private HttpServletResponse response; //HTTP 응답 할 때 정보와 기능이 포함된 객체


    //[2] 파잏 다운로드
    public  void fileDownLoad(String filename){

        System.out.println("filename = " + filename);
        System.out.println("FileService.fileDownLoad");
        //1. 다운로드 할 경로 설정 uploadPath
        //1. 업로드 된 경로와 다운로드할 파일명 조합
        String downLoadPath=uploadPath + filename;
        //exists() : 해당 경로의 파일이 존재하면 true , 없으면 false
        //length() : 해당 경로의 파일이 존재하면 파일의 용량을 바이트의 개수로 반환 [ 용량 찾기 ]
        File file =new File(downLoadPath);
        if (!file.exists()){ //파일 존재하지 않으면
            return;
        }

        //해당 다운로드할 파일을 서버 (자바) 로 바이트를 읽기
        //스트림이란 : 자바 외부 와 통시니 바이트가 다니는 통로
        // inputStream : 읽어들이는 통로 , OutPutStream : 내보내는 통로
        // Buffered , 버퍼 : 특정 위치로 이동하는 동안 일시적으로 데이트럴 보관하는 메모리(스트림)


        try {
            //-----------파일 바이트 배열로 읽어오자
            //2-1 파일 입력 스트림 객체 생성
            ///BufferedInputStream fin = new BufferedInputStream(new FileInputStream(downLoadPath));
            FileInputStream fin=new FileInputStream(downLoadPath);
            //2-2 파일의 용량만큼 배열의 길이 선언
            long fileSize = file.length();
            //2-3 파일의 용량만큼 배열의 길이 선언
            byte[] bytes=new byte[(int)fileSize];
            //.read(배열명) : 해당 파일을 읽어서 바이트들을 해당 배열에 하나씩 대입한다.
            fin.read(bytes); //경로에 해당하는 파일을 바이트로 읽어들이기

            fin.close(); //버퍼닫기

            System.out.println(Arrays.toString(bytes));
            //-------------읽어온 바이트배열을 HTTP 바이트 형식으로 응답하기-------------

            //[3] HTTP 스트림으로 응답하기
            //3-1 출력스트림new BufferedOutputStream(출력할 대상의 스트림 객체)
            //response.getOutputStream() : HTTP 응답시 바이트 형식의 스트림 사용
            //BufferedOutputStream fout = new BufferedOutputStream(response.getOutputStream() );
            ServletOutputStream fout = response.getOutputStream();

            //---HTTP 응답의 헤어 속성 추가 .setHeader(key, value)
            //Content-Disposition : 브라우저가 제공하는 다운로드 형식
            //attachemt;filename=(다운로드에 표시될 파일명)
            //-URLEncoder.encode(): URL 경로상의 한글을 인코딩
            //filename.split("_")[1] : '_'기준으로 분해해서 UUID 를 제외한 실제 파일명만 추출
            response.setHeader(
                    "Content-Disposition" ,
                    "attachment;filename="  + URLEncoder.encode( filename.split("_")[1] , "utf-8") );
            //3-2 바이트 배열 내보내기 /출력/쓰기
            fout.write(bytes);
            fin.close();

        }catch (Exception e){
            System.out.println("e = " + e);
        }

    }

}
