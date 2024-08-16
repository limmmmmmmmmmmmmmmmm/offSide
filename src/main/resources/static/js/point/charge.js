console.log('charge.js');

let price = 0;          // 충전할 금액을 받을 변수
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

// 충전할 금액 변수에 저장
function getPrice(chargePrice) {   console.log('getPrice()');
    console.log(chargePrice);

    price = chargePrice;
}   // getPrice() end

// 포인트 충전 함수
function doPointCharge() {  console.log('doPointCharge()');
    let depositorName = document.querySelector('.depositorName').value;
    console.log(depositorName);

    if (price == 0) {
        alert('충전하실 금액 버튼을 선택해주시기 바랍니다.');
        return;
    }
    if (depositorName == '') {
        alert('입금자명을 입력해주시기 바랍니다.');
        return;
    }

    let info = {
        pindecrease : price,             // 충전할 금액
        accountlog : depositorName,      // 입금자명
        mno : loginMno                   // 로그인된 회원번호
    }

    $.ajax({
        async : false,
        method : "post",
        url : "/point/charge",
        data : JSON.stringify( info ) ,
        contentType : "application/json" ,
        success : (result) => {     console.log(result);
            if (result) {
                alert('2시간 내로 선택하신 금액을 00은행 000-000-00-0000으로 입금해주시기 바랍니다. 정상 입금 확인 후 포인트 충전 신청 승인을 해드리겠습니다.')
                location.href = "/"
            }
        }   // success end
    })  // ajax end

}   // doPointCharge() end
