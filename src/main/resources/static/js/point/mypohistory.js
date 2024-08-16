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
                    <li class="poinfo3">+${ r.pindecrease}  </li>
                    <p>나의 현재 포인트는 ${r.pindecrease} 입니다.</p><br/>
                    `
                    console.log(html);
            })
        }


    }); console.log(html);
    //출력
    pointPrintBox.innerHTML=html;
}





