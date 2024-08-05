console.log('delete.js');

// 회원 탈퇴 함수
function memberDelete() {
    let pw = document.querySelector('.pw').value;

    $.ajax({
        async : false,
        method : 'delete',
        url : '/member/delete',
        data : { mpw : pw },
        success : (result) => { console.log(result);
            if (result) {
                alert('회원 탈퇴에 성공했습니다.')
                location.href = '/'
            } else {
                alert('입력한 정보가 일치하지 않습니다.');
            }
        }

    });  // ajax end

}   // memberDelete end
