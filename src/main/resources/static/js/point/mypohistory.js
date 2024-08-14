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
        url: "/point/history" ,
        success: result =>{
            console.log(result);
            html+= `<li id="pointColor"> ${result.preason}</li>
                    <li class="poinfo"> ${result.pregistration}  </li> <br/><br/>
                    `
                    console.log(html);
        }


    }); console.log(html);
    //출력
    pointPrintBox.innerHTML=html;
}





