console.log('update.js');

let urlParams = new URL(location.href).searchParams;
let bno = urlParams.get("bno") // 현재 URL 경로상의 bno 값 호출

readUpdate();

//게시물 bno 별 개별조회
function readUpdate(){console.log('readUpdate()');
    let board = {}; // 이거는 왜 넣었는지 모르겠다

        $.ajax({
            async : false ,
            method:'get',
            url:"/board/bread",
            data:{bno:bno},
             // bno 넘겨주기
            success:(r) =>{
               console.log('출력함수');
               console.log(r);
               board = r;
            }
        })  // ajax end
        // 1. bno별 각자 게시물 출력 / innerHTMl이 아닌 value로 출력 값을 가져와야 하니까
        document.querySelector(".btitle").value = `${board.btitle}`;
        document.querySelector("#sample6_detailAddress").value = `${board.baddress}`;
        document.querySelector(".bdatetime").value = `${board.bdatetime}`;
        document.querySelector(".bprice").value = `${board.bprice}`;
        document.querySelector(".btnBox").innerHTML = `<button type="button" onclick="bUpdate()">수정</button>
                                                       <button type="button">삭제</button>`;

        
}   // boardRead() end}


//게시물 수정 함수

function bUpdate(){ console.log('bUpdate()');
    // 수정해야 할 데이터들 값 입력 가져오기
    let btitle = document.querySelector(".btitle").value;
    // - 주소 호출
    let sample6_address =document.querySelector('#sample6_address').value;
    let sample6_detailAddress= document.querySelector('#sample6_detailAddress').value;
    let bdatetime = document.querySelector(".bdatetime").value;
    let bprice = document.querySelector(".bprice").value;

    let fullAddress = `${sample6_address} ${sample6_detailAddress}`;

    let info = { //info 에 bno까지 같이 넣어주고
        btitle : btitle , 
        baddress:fullAddress ,
        bdatetime : bdatetime ,
        bprice : bprice,
        bno : bno
    }
    console.log(info);
    
    $.ajax({
        async : false,
        method : "put",
        url : "/board/update",
        data : JSON.stringify(info) , 
        contentType : "application/json" , 
        success : (r)=>{
            console.log('수정함수');
            console.log(r);
            if(r){
                alert('수정성공');
                location.href="/admin/board";
            }else{
                alert('입력한 정보가 일치하지 않습니다.');
            }
        } , // success end
        error : (e)=>{console.log(e);}
        
    }); // ajax end
}

  //다음 지도 함수
  function sample6_execDaumPostcode() {
         new daum.Postcode({
             oncomplete: function(data) {
                 // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                 // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                 // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                 var addr = ''; // 주소 변수
                 var extraAddr = ''; // 참고항목 변수

                 //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                 if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                     addr = data.roadAddress;
                 } else { // 사용자가 지번 주소를 선택했을 경우(J)
                     addr = data.jibunAddress;
                 }

                 // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                 if(data.userSelectedType === 'R'){
                     // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                     // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                     if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                         extraAddr += data.bname;
                     }
                     // 건물명이 있고, 공동주택일 경우 추가한다.
                     if(data.buildingName !== '' && data.apartment === 'Y'){
                         extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                     }
                     // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                     if(extraAddr !== ''){
                         extraAddr = ' (' + extraAddr + ')';
                     }
                     // 조합된 참고항목을 해당 필드에 넣는다.
                     document.getElementById("sample6_extraAddress").value = extraAddr;

                 } else {
                     document.getElementById("sample6_extraAddress").value = '';
                 }

                 // 우편번호와 주소 정보를 해당 필드에 넣는다.
                 document.getElementById('sample6_postcode').value = data.zonecode;
                 document.getElementById("sample6_address").value = addr;
                 // 커서를 상세주소 필드로 이동한다.
                 document.getElementById("sample6_detailAddress").focus();
             }
         }).open();
     }