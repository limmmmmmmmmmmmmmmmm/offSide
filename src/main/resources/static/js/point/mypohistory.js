console.log('mypohistory.js');

pointPrint();
function pointPrint(){
    //어디에
    let pointPrintBox= document.querySelector('#pointPrintBox');
    //무엇을
    let html='';
    console.log('html')
    $.ajax({
        async:false ,
        method: 'get' ,
        url: "/member/history" ,
        success: result =>{
            result.forEach( r => {

            console.log(result);
            html+= `<li id="pointColor"> ${r.preason}</li>
                    <li class="poinfo"> ${r.pregistration}  </li>
                    <li class="poinfo3">+${ r.pindecrease} </li>`
            console.log(r.pstate);
            if(r.pstate==0){
              html+=`관리자 승인여부<li class="poinfo">대기중 </li> <br/>`
            }
            else{
              html+=`관리자 승인여부<li class="poinfo">승인완료 </li> <br/>`
            }
                    console.log(html);
            })
        }


    }); console.log(html);
    //출력
    pointPrintBox.innerHTML=html;
}





