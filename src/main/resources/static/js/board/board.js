console.log('board.js');

// 구장 목록 출력
boardPrint();
function boardPrint() {     console.log('boardPrint()');
    // ajax로부터 응답받은 객체를 저장할 변수
    let boardDto = { };

    $.ajax({
        async : false,
        method : "get",
        url : "/board/list",
        success : (result) => {     console.log(result);
            boardDto = result;
            console.log(boardDto);
        }   // success end
    })  // ajax end

    // 어디에
    let boardWrap = document.querySelector('.boardWrap');

    // 구장 목록을
    let html = ``;

    boardDto.forEach ( b => {
        let mno = loginCheck();
        console.log(mno);
        html += `<div id="warp">
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

                    <div>
                        <button type="button" onclick="location.href='#'">예약</button>
                    </div>
                `
        if (mno == 1) {
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
