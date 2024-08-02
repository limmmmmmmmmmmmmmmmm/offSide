console.log('signup.js');


function doSignup() {

    // 1. 입력값 가져오기
    let id = document.querySelector('#id').value;
    let pw = document.querySelector('#pw').value;
    let name = document.querySelector('#name').value;
    let phone = document.querySelector('#phone').value;
    let mgender = document.querySelector('#mgender').value;
    let mbirth = document.querySelector('#mbirth').value;
    let maccount = document.querySelector('#maccount').value;

    // 2. 객체
    let info = {
        id: id, pw: pw, name: name, phone: phone,
        mgender: mgender, mbirth: mbirth, maccount: maccount
    };

    // 3. ajax
    $.ajax({
        async: false,
        method: 'post',
        url: 'member/signup',
        data: info,
        succes: (result) => {
            console.log(result);
            // 4. 결과값 처리
            if (result) {
                alert('회원가입 성공');
            } else { alert('회원가입 실패'); }

        }   // succes end
    });  // ajax end


}   // doSignup end
