console.log('myinfo.js');

Mmyinfo();
function Mmyinfo(){
    console.log('Mmyinfo()');
     //어디에
     let myinfoBox= document.querySelector('.myinfoBox');
     //무엇을
     let html ='';
    $.ajax({
        method: 'get' ,
        url: "/member/my/info2" ,
        success: result => {
            console.log(result);
//            if(result == ''){
//                alert('로그인 하구 와 ~');
//                location.href="/member/login";
//
//            }

            html += `<div class="_mtitle">${result.mname}님의 아이디</div> <div> ${result.mid} </div> <br/>
                    <div class="_mtitle">이름</div> <div> ${result.mname} </div> <br/>
                    <div class="_mtitle">휴대폰 번호 </div> <div> ${result.mphone} </div> <br/>
                    <div class="_mtitle">성별 </div> <div> ${result.mgender} </div> <br/>
                    <div class="_mtitle">생일 </div> <div> ${result.mbirth} </div> <br/>
                    <div class="_mtitle">환불계좌 </div> <div> ${result.maccount} </div> <br/>
`
        }


    })
    myinfoBox.innerHTML= html;
}