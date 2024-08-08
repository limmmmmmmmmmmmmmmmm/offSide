console.log('update.js');

let urlParams = new URL(location.href).searchParams;
let bno = urlParams.get("bno") // 현재 URL 경로상의 bno 값 호출

readUpdate();
//게시물 bno 별 개별조회
function readUpdate(){
    let board = {}; // 이거는 왜 넣었는지 모르겠다
        $.ajax({
            async : false ,
            method:'get',
            url:"/board/bread",
            data:{bno:bno}, // bno 넘겨주기
            success:(r) =>{
               console.log(r);
               board = r;
            }
        })  // ajax end
        // 1. 어디에
        document.querySelector(".btitle").innerHTML = `${board.btitle}`;
        document.querySelector(".baddress").innerHTML = `${board.baddress}`;
        document.querySelector(".bdatetime").innerHTML = `${board.bdatetime}`;
        document.querySelector(".bprice").innerHTML = `${board.bprice}`;
        document.querySelector(".btnBox").innerHTML = `<button type="button">수정</button>
                                                       <button type="button">삭제</button>`;
    }   // boardRead() end
}


//게시물 수정 함수
