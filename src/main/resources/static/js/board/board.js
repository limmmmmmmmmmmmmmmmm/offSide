console.log('board.js');

// 구장 목록 출력
boardPrint();
function boardPrint() {     console.log('boardPrint()');
    // 어디에 구장 목록을 출력할 건지
    let boardWrap = document.querySelector('.boardWrap');

    // 무엇을
    let html = ``;

    $.ajax({
        async : false,
        method : "get",
        url : "/board/list",
        success : (result) => {     console.log(result);
            

        }   // success end
    })  // ajax end

}   // boardPrint() end
