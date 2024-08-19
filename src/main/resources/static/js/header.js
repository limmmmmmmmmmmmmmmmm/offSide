console.log('header.js');
let pindecrease;
printadd();

// 로그인 상태 확인
loginCheck();
function loginCheck() {    console.log('loginCheck()');
    let mno;

    $.ajax({
        async : false,
        method : 'get',
        url : '/member/login/check',
        success : (result) => {     console.log(result);
            let html = ``;

            mno = result.mno;

            // 로그인 상태에 따른 메뉴 구성
            if (result == '') {
                // 비로그인 상태인 경우
                console.log('비로그인');
                html += `
                        <a href="/"> <img class="Logo1" src="/picture/오프사이드_로고.png" /> </a>
                        <li> <a href="/member/login"> <img class="Logo2" src="/picture/로그인이미지.png" /> </a> </li>


                        `
            } else if (result.mno == 1) {   // 관리자의 mno는 1
                // 관리자로 로그인한 경우
                console.log('관리자 로그인');
                html += `
                        <a href="/"> <img class="Logo1" src="/picture/오프사이드_로고.png" /> </a>
                        <li> <a href="/point/charge"> 포인트 충전 </a> </li>
                        <li> <a href="/admin/board"> 관리자 페이지 </a> </li>
                        <li> <a href="#" onclick="logout()"> 로그아웃 </a> </li>
                        `
            } else {console.log(pindecrease);
                // 일반 회원이 로그인한 경우
                console.log('일반 회원 로그인');
                html += `
                        <a href="/"> <img class="Logo1" src="/picture/오프사이드_로고.png" /> </a>
                        <li>  ${result.mname}님 </li>
                        <li>  포인트 ${pindecrease} </li>
                        <li> <a href="/point/charge"> 포인트 충전 </a> </li>
                        <li> <a href="/member/my/info"> 내정보 </a> </li>
                        <li> <a href="#" onclick="logout()"> 로그아웃 </a> </li>
                        `
            }

            // 메뉴 출력
            document.querySelector('#loginMenu').innerHTML = html;
            
        }   // success end
    });  // ajax end

    console.log(mno);
    return mno;
}   // loginCheck() end


// 로그아웃
function logout() { console.log('logout()');
    $.ajax({
        async : false,
        method : 'get',
        url : '/member/logout',
        success : (result) => { console.log(result);
            alert('로그아웃되었습니다.');
            location.href = "/";
        }
    });     // ajax end

}   // logout() end



function printadd(){console.log('printadd()');
    $.ajax({
        async:false,
        method:'get',
        url:"/member/add",
        success:function(result){ console.log(result);
           //html= `${result}원`
           pindecrease=result;

        }
    });

}
