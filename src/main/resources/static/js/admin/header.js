console.log('/admin/header.js');

// 로그인 상태 확인
loginCheck();
function loginCheck() {    console.log('loginCheck()');
    $.ajax({
        async : false,
        method : 'get',
        url : '/member/login/check',
        success : (result) => {     console.log(result);
            mno = result.mno;
            let html = ``;

            // 로그인 상태에 따른 권한 부여
            if (result.mno == 1) {   // 관리자의 mno는 1
                // 관리자로 로그인 후 접근한 경우
                console.log('관리자 접근');
            } else {
                // 비로그인 상태 또는 일반 회원이 접근한 경우
                console.log('비로그인/일반 회원 접근');
                alert('관리자만 접근할 수 있습니다.');
                location.href = "/";
            }
        }   // success end
    });  // ajax end
    return 1;

}   // loginCheck() end