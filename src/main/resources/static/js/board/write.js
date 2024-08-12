console.log('write.js');


 function _boardWrite(){
     //1. form 가져오기 ,form 안에 있는 html 모두 한번에 가져오기
     let boardWrite=document.querySelector('.boardWrite')
     console.log(boardWrite);

      //2. form  HTML 를 바이트로 변환해주는 객체 = new FormData
     let boardWriteFormData= new FormData(boardWrite) ;
     console.log(boardWriteFormData);
     $.ajax({
         async:false ,
         method: 'post' ,
         url: "/board/write",
         data: boardWriteFormData ,
         contentType: false , processData : false,
         success:  r => {console.log(r);
                         alert('구장등록성공!')
                        location.href="/admin/board";
                     },
                        error: e => {console.log(e);
                         alert('구장 등록 실패')
                        }




     })

 }