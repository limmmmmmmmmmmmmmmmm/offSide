console.log('signup.js');
let checkArray = [false, false, false, false, false];

//    아이디, 비밀번호 ,이름 , 전화번호 , 계좌번호
function doSignup() {

    for (let i = 0; i < checkArray.length; i++) {
        if (!checkArray[i]) {
            alert(i);
            alert('유효하지 않는 정보가 있습니다.');
            return;
        }
    }

    console.log('doSignup()');
    // 1. 입력값 가져오기
    let mid = document.querySelector('#id').value;
    let mpw = document.querySelector('#pw').value;
    let mname = document.querySelector('#name').value;
    let mphone = document.querySelector('#phone').value;
    let mgender = document.querySelector('#mgender').value;
    let year = document.querySelector('#year').value;
    let month = document.querySelector('#month').value;
    let day = document.querySelector('#day').value;
    let maccount = document.querySelector('#maccount').value;
    let mbirth = year + "-" + month + "-" + day;
    console.log(mbirth);

    // 2. 객체
    let info = {
        mid: mid, mpw: mpw, mname: mname, mphone: mphone,
        mgender: mgender, mbirth: mbirth, maccount: maccount
    };
    console.log(info);
    // 3. ajax
    $.ajax({
        async: false,
        method: 'post',
        url: '/member/signup',
        data: info,
        success: (result) => {
            console.log(result);
            // 4. 결과값 처리
            if (result) {
                alert('회원가입 성공');
            } else { alert('회원가입 실패'); }

        }   // succes end
    });  // ajax end
}   // doSignup end

// 2. 아이디 유효성 검사
function idCheck() {
    let mid = document.querySelector('#id').value;
    let idCheckBox = document.querySelector('.idCheckBox');

    let idReg = /^[a-zA-Z0-9]{3,30}$/;
    // 아이디 중복검사 REST 통신
    if (idReg.test(mid)) {
        $.ajax({
            async: false,
            method: "get",
            url: "/member/idcheck",
            data: { mid: mid },
            success: (result) => {
                idCheckBox.innerHTML = `사용중인 아이디`;
                checkArray[0] = false;
                if (result) {
                    idCheckBox.innerHTML = `사용중인 아이디`;
                    checkArray[0] = false;
                } else {
                    idCheckBox.innerHTML = `사용가능한 아이디 입니다.`;
                    checkArray[0] = true;
                }
            } // success method end
        }); // ajax end
    } else {
        idCheckBox.innerHTML = `영대소문자 와 숫자 조합의 3~30 글자 사이 가능합니다.`;
        checkArray[0] = false;
    }
} // method end

// 3. 패스워드 유효성 검사
function pwCheck() {
    let pw = document.querySelector('#pw').value;
    let pwConfirm = document.querySelector('#pwConfirm').value;
    let pwCheckBox = document.querySelector('.pwCheckBox');

    let pwReg = /^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z0-9]{5,30}$/;

    if (pwReg.test(pw)) {
        if (pwReg.test(pwConfirm)) {
            if (pw == pwConfirm) {
                pwCheckBox.innerHTML = '통과';
                checkArray[1] = true;
                return;
            } else {
                pwCheckBox.innerHTML = '두 비밀번호가 일치하지 않습니다.';
                checkArray[1] = false;
                return;
            }
        }
    }
    pwCheckBox.innerHTML = `영대소문자 와 숫자 조합의 5~30 글자 사이 가능합니다.`;
    checkArray[1] = false;

}// method end

// 4. 이름 유효성검사
function nameCheck() {
    let name = document.querySelector('#name').value;
    let nameCheckBox = document.querySelector('.nameCheckBox');
    let nameReg = /^[가-힣]{2,20}$/;
    if (nameReg.test(name)) {
        nameCheckBox.innerHTML = '사용가능한 이름입니다.';
        checkArray[2] = true;
    } else {
        nameCheckBox.innerHTML = '한글 2~20글자 사이 입력해주세요.';
        checkArray[2] = false;
    }
}// method end

// 5. 전화번호 유효성검사.
function phoneCheck() {
    let phone = document.querySelector('#phone').value;
    let phoneCheckBox = document.querySelector('.phoneCheckBox');

    let phoneReg = /^([0-9]{2,3})+[-]+([0-9]{3,4})+[-]([0-9]{4})$/;
    if (phoneReg.test(phone)) {
        phoneCheckBox.innerHTML = '사용가능한 전화번호입니다.';
        checkArray[3] = true;
    } else {
        phoneCheckBox.innerHTML = '000-0000-0000 또는 00-000-0000 형식으로 입력해주세요.';
        checkArray[3] = false;
    }
}// method end

// 6. 계좌번호 유효성검사.
function maccountCheck() {
    let maccount = document.querySelector('#maccount').value;
    let maccountBox = document.querySelector('.maccountBox');

    let maccounReg = /^([0-9]{3,4})+[-]+([0-9]{5,6})+[-]([0-9]{5,6})$/;
    if (maccounReg.test(maccount)) {
        maccountBox.innerHTML = '사용가능한 계좌번호 입니다.';
        checkArray[4] = true;
    } else {
        maccountBox.innerHTML = '000-00000-00000 또는 0000-000000-000000 형식으로 입력해주세요.';
        checkArray[4] = false;
    }
}// method end

// 7. 생년월일
document.addEventListener('DOMContentLoaded', () => {
    const yearSelect = document.getElementById('year');
    const monthSelect = document.getElementById('month');
    const daySelect = document.getElementById('day');

    // 년도 선택 옵션 생성
    for (let year = 1900; year <= new Date().getFullYear(); year++) {
        yearSelect.innerHTML += `<option value="${year}">${year}</option>`;
    }

    // 월 선택 옵션 생성
    for (let month = 1; month <= 12; month++) {
        monthSelect.innerHTML += `<option value="${month}">${month}</option>`;
    }

    // 일 선택 옵션 생성
    for (let day = 1; day <= 31; day++) {
        daySelect.innerHTML += `<option value="${day}">${day}</option>`;
    }
});








