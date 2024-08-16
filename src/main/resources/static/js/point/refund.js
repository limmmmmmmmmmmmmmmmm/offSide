console.log('refund.js');

//let price = 0;          // 환불할 금액을 받을 변수
let loginMno = 0;       // 현재 로그인된 회원번호를 받을 변수 (0이면 비로그인)

// 로그인 체크
doLoginCheck();
function doLoginCheck() {
    let mno = loginCheck();
    console.log(mno);

    if (undefined === mno) {
        alert('로그인 후 이용 가능합니다.');
        location.href = '/member/login';
    }

    loginMno = mno;
}   // doLoginCheck() end

// 포인트 환불 함수
function doPointRefund() {  console.log('doPointRefund()');
    let refundPrice = document.querySelector('#refundPrice').value;                 console.log(refundPrice);
    let accountHolderName = document.querySelector('.accountHolderName').value;     console.log(accountHolderName);
    let bankName = document.querySelector('.bankName').value;                       console.log(bankName);
    let account = document.querySelector('.account').value;                         console.log(account);
    
    if (refundPrice == '') {
        alert('환불하실 금액 버튼을 선택해주시기 바랍니다.');
        return;
    }
    if (accountHolderName == '') {
        alert('계좌주명을 입력해주시기 바랍니다.');
        return;
    }
    if (bankName == '') {
        alert('은행명을 입력해주시기 바랍니다.');
        return;
    }
    if (account == '') {
        alert('계좌번호를 입력해주시기 바랍니다.');
        return;
    }

    let info = {
        pindecrease : refundPrice,                                       // 환불할 금액
        accountlog : `${accountHolderName}_${bankName}_${account}`,      // 계좌주명_은행명_계좌번호
        mno : loginMno                                                   // 로그인된 회원번호
    }
    console.log(info);

    $.ajax({
        async : false,
        method : "post",
        url : "/point/refund",
        data : JSON.stringify( info ) ,
        contentType : "application/json" ,
        success : (result) => {     console.log(result);
            if (result) {
                alert('환불 신청이 완료되었습니다.')
                location.href = "/"
            }
        }   // success end
    })  // ajax end

}   // doPointRefund() end

// 환불 가능 금액 계산
document.addEventListener('DOMContentLoaded', () => {
    const refundPriceSelect = document.getElementById('refundPrice');

    // 환불 금액 선택 옵션 생성
    for (let refundPrice = 1000; refundPrice <= 30000; refundPrice += 1000) {
        refundPriceSelect.innerHTML += `<option value="${refundPrice}">${refundPrice}</option>`;
    }
});
