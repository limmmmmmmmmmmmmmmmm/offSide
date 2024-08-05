console.log('update.js');

// 1. 회원 정보 출력
myinfoPrint();
function myinfoPrint() {    console.log('myinfoPrint()');
    $.ajax({
        async : false,
        method : 'get',
        url : '/member/my/info2',
        success : result => {   console.log(result);
            if ('' == result) {
                alert('로그인 후 이용 가능합니다.');
                location.href = '/member/login';
            }

            // 회원의 회원 번호, 아이디, 성별, 생년월일 보여주기
            document.querySelector('.no').value = `${result.mno}`;
            document.querySelector('.id').value = `${result.mid}`;
            document.querySelector('.gender').value = `${result.mgender}`;
            document.querySelector('.birth').value = `${result.mbirth}`;

            document.querySelector('.name').value = `${result.mname}`;
            document.querySelector('.phone').value = `${result.mphone}`;
            document.querySelector('.account').value = `${result.maccount}`;
        }   // success end

    });  // ajax end
    
}   // myinfoPrint() end



// 2. 회원 정보 수정
function memberUpdate() {   console.log('memberUpdate()');
    // 1. 보낼 데이터 수집
    let pw = document.querySelector('.pw').value;
    let newpw = document.querySelector('.newpw').value;
    let name = document.querySelector('.name').value;
    let phone = document.querySelector('.phone').value;
    let account = document.querySelector('.account').value;

    let info = {
        mpw : pw,
        newpw : newpw,
        mname : name,
        mphone : phone,
        maccount : account
    }

    // 2.
    $.ajax({
        async : false,
        method : 'put',
        url : '/member/update',
        data : JSON.stringify(info),
        contentType : "application/json",
        success : result => {   console.log(result);
            if (result) {
                alert('회원 정보 수정을 완료했습니다.');
                location.href = '/member/my/info'
            } else {
                alert('입력한 정보가 일치하지 않습니다.');
            }
        },
        error : e => {
            console.log(e);
        }

    });   // ajax end
}   // memberUpdate() end

