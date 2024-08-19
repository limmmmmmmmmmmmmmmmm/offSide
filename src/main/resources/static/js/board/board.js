console.log('board.js');

// 구장 목록 출력
boardPrint();
function boardPrint() {     console.log('boardPrint()');
    // ajax로부터 응답받은 객체를 저장할 변수
    let boardDto = { };
    let searchKey = document.querySelector(".searchKey").value;
    let searchKeyword = document.querySelector(".searchKeyword").value;

    console.log( searchKey )
    console.log( searchKeyword )

    $.ajax({
        async : false,
        method : "get",
        url : "/board/list",
        data : { 'searchKey' : searchKey , 'searchKeyword' : searchKeyword} ,
        success : (result) => {     console.log(result);
            boardDto = result;
            console.log(boardDto);
        },   // success end
        error : (e) =>{
              console.log(e);
        }
    })  // ajax end

    // 어디에
    let boardWrap = document.querySelector('.boardWrap');

    // 구장 목록을
    let html = ``;

    boardDto.forEach ( b => {
        let mno = loginCheck();
        console.log(mno);
        html += `<div class="warp">
                    <div id="carouselExample${b.bno}" class="carousel slide" data-ride="carousel">
                        <div class="carousel-inner">
                            <div class="carousel-item active">
                                <img src="/upload/${b.bfile1}" class="d-block w-100" alt="..." style="width:400px; height: 300px" />
                            </div>
                            <div class="carousel-item">
                                <img src="/upload/${b.bfile2}" class="d-block w-100" alt="..." style="width:400px; height: 300px" />
                            </div>
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample${b.bno}" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExample${b.bno}" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        </button>
                    </div>
                    
                    <div>
                        ${b.bdatetime}
                    </div>

                    <div>
                        ${b.btitle}
                    </div>

                    <div>
                        ${b.baddress}
                    </div>

                    <div>
                        ${b.bprice}
                    </div>
                `;

        $.ajax({
            async : false ,
            method : 'get' ,
            url : "reservation/effectiveness" ,
            data : { bno : b.bno } ,
            success : result2 => {
                if( result2 ){ // 만약에 현재 bno가 내가 예약한 bno 이면 구장 예약중 이고 아니면 구장 예약 버튼
                    html += `구장 예약 중`
                }else{
                    html += `<div> <button type="button" onclick="reservation(${b.bno})"> 구장 예약 </button>  </div>`
                }
            }
        });


        if (mno == 1) { // mno=1 관리자 회원번호 , 만약에 관리자이면 수정/삭제 할수 있도록 버튼 보이기
            html += `
                    <div>
                        <button type="button" onclick="location.href='/board/update?bno=${b.bno}'">수정</button>
                        <button type="button" onclick="bDelete(${b.bno})">삭제</button>
                    </div>
                    `
        }

        html += `</div> <br>`
    });

    // 출력
    boardWrap.innerHTML = html;

}   // boardPrint() end


// 구장 게시글 삭제 함수
function bDelete(bno){
    $.ajax({
        async : false,
        method : 'delete',
        data : {bno : bno},
        url : "/board/delete",
        success : (r)=>{
            console.log(r);
            if(r){
                alert('삭제 성공');
                boardPrint();
            } else{
                alert('삭제 불가능');
            }
        }     // success end
    });  // ajax end

}   // bDelete() end



// 회원 구장 예약 함수
function reservation(bno){
    $.ajax({
            async : false,
            method : 'post',
            data : {bno : bno},
            url : "/reservation/stadiumreservation",
            success : (r)=>{ console.log(r);
                if(r){
                    alert('예약 성공');
                    boardPrint();
                } else{
                    alert('포인트가 부족 합니다.');
                }
            }     // success end
        });  // ajax end

} // reservation end
