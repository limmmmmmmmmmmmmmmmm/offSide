console.log('update.js');

let urlParams = new URL(location.href).searchParams;
let bno = urlParams.get("bno") // 현재 URL 경로상의 bno 값 호출

readUpdate();

//게시물 bno 별 개별조회
function readUpdate(){console.log('readUpdate()');
    let board = {}; // 이거는 왜 넣었는지 모르겠다
        $.ajax({
            async : false ,
            method:'get',
            url:"/board/bread",
            data:{bno:bno},
             // bno 넘겨주기
            success:(r) =>{
               console.log('출력함수');
               console.log(r);
               board = r;
            }
        })  // ajax end
        // 1. 어디에
        document.querySelector(".btitle").value = `${board.btitle}`;
        document.querySelector(".baddress").value = `${board.baddress}`;
        document.querySelector(".bdatetime").value = `${board.bdatetime}`;
        document.querySelector(".bprice").value = `${board.bprice}`;
        document.querySelector(".btnBox").innerHTML = `<button type="button" onclick="bUpdate()">수정</button>
                                                       <button type="button">삭제</button>`;

        
                                                    }   // boardRead() end}


//게시물 수정 함수

function bUpdate(){ console.log('bUpdate()');
    let btitle = document.querySelector(".btitle").value;
    let baddress = document.querySelector(".baddress").value;
    let bdatetime = document.querySelector(".bdatetime").value;
    let bprice = document.querySelector(".bprice").value;

    let info = {
        btitle : btitle , 
        baddress : baddress , 
        bdatetime : bdatetime , 
        bprice : bprice,
        bno : bno
    }
    console.log(info);
    
    $.ajax({
        async : false,
        method : "put",
        url : "/board/update",
        data : JSON.stringify(info) , 
        contentType : "application/json" , 
        success : (r)=>{
            console.log('수정함수');
            console.log(r);
            if(r){
                alert('수정성공');
                location.href="/admin/board";
            }else{
                alert('입력한 정보가 일치하지 않습니다.');
            }
        } , // success end
        error : (e)=>{console.log(e);}
        
    }); // ajax end
}