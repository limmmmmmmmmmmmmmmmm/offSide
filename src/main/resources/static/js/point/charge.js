console.log('charge.js');

// 포인트 충전 함수
function doPointCharge() {  console.log('doPointCharge()');
    

    $.ajax({
        async : false,
        method : "post",
        url : "/point/charge",
        data : {
            pindecrease : "10000" ,
            accountlog : "홍길동"
        },
        success : (result) => {     console.log(result);
            if (result) {
                alert('2시간 내로 00은행 000-000-00-0000로 입금해주시면 포인트 충전 신청 승인을 해드리겠습니다.')
                location.href = "/"
            }
        }
    })

}
