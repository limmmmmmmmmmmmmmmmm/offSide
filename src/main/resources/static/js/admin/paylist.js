console.log('paylist.js');

dopaylist();
function dopaylist(){  console.log('dopaylist()');
    
    let board = {};
    // 어디에
    let payListBox = document.querySelector('#payListBox');

    // 구장 목록을
    let html = ``;

    $.ajax({
        async : false ,
        method:"get",
        url:"/point/paylist",
        success:(r) =>{
           console.log('출력함수');
           console.log(r);
           board = r;
        } //success end
    })  // ajax end
    board.forEach ( p => {      html += `
                                         <div> ${p.mid}</div>
                                         <div>${p.mname}</div>
                                         <div>${p.pindecrease}</div>
                                         <div>${p.preason}</div>
                                         <div>${p.accountlog}</div>
                                         <div>${p.papprovedate}</div>
                                         <div>${p.pregistration}</div>`
                                if( p.pstate == 0 ){
                                    html +=`<button type="button" onclick="payAgree(${p.pno})">포인트 승인</button>`;
                                }else{
                                    html +=`<button type="button" onclick="payAgree(${p.pno})">포인트 승인 해제</button>`;
                                }
                                         
                           });

    payListBox.innerHTML = html;
}

function payAgree(pno){ console.log('payAgree()');
    console.log(pno);
    
    

    let info = { //info 에 pno 넣어주고
        pno : pno 
    }
    console.log(info);
    
    $.ajax({
        async : false,
        method : "put",
        url : "/point/payagree",
        data : JSON.stringify(info) , 
        contentType : "application/json" ,
        success : (r)=>{
            console.log('수정함수');
            console.log(r);
            if(r){
                alert('수정성공');
                //회원칸 삭제 하기 해야함
                dopaylist()//새로고침
            }else{
                alert('입력한 정보가 일치하지 않습니다.');
            }
        } , // success end
        error : (e)=>{console.log(e);}
        
    }); // ajax end
}