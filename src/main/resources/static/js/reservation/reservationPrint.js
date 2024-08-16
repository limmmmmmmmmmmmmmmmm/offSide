console.log('reservationPrint.js');

// 내가 예약한 구장 목록 출력
myReservationPrint();
function myReservationPrint() {     console.log('myReservationPrint()');
    let reservationDto = { };

    $.ajax({
        async : false,
        method : 'get',
        url : '/reservation/my/print',
        success : (result) => { console.log(result);
            reservationDto = result;
            console.log(reservationDto);
        }   // success end

    });  // ajax end

    // 어디에
    let wrap = document.querySelector('#wrap');

    // 예약 목록을
    let html = ``;

    reservationDto.forEach ( r => {
        html += `<div class="reservation">
                    <div>
                        ${r.bdatetime}
                    </div>

                    <div>
                        ${r.btitle}
                    </div>

                    <div>
                        ${r.baddress}
                    </div>

                    <div>
                        ${r.bprice}
                    </div>

                    <div>
                        예약날짜 : ${r.rdate}
                    </div>
                `
        if (r.rstate == 0) {
            html += `
                    <div>
                        예약 취소 완료
                    </div>
                </div> <br>
                `
        } else {
            html += `
                    <div>
                        예약 완료
                    </div>
                    <div>
                        <button type="button" onclick="cancellation(${r.bno})">예약 취소하기</button>
                    </div>
                </div> <br>
                `
        }
    });

    // 출력
    wrap.innerHTML = html;

}   // myReservationPrint() end

// 회원 구장 예약 취소
function cancellation(bno){
    $.ajax({
                async : false,
                method : 'put',
                data : {bno : bno},
                url : "/reservation/stadiumcancellation",
                success : (r)=>{ console.log(r);
                    if(r){
                        alert('취소 성공');
                        myReservationPrint();
                    } else{
                        alert('취소 실패');
                    }
                }     // success end
            });  // ajax end
    }   // cancellation end