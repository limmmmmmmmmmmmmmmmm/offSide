console.log('pointadd.js');
printadd();
function printadd(){console.log('printadd()');
    //어디에
    let pointaddBox= document.querySelector('.pointaddBox')
    //무엇을
    let html='';
    console.log('html')
    $.ajax({
        async:false,
        method:'get',
        url:"/member/add",
        success:function(result){ console.log(result);
           html= `${result}원`

        }
    });
    //출력
    pointaddBox.innerHTML=html;

}
