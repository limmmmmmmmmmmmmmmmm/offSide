console.log('delete.js');

function bDelete(bno){

    $.ajax({
        async : false,
        method : 'delete',
        data : {bno : bno},
        url : "/board/delete",
        success : (r)=>{
            console.log(r);
            if(r){
                alert('삭제 성공');
            }else{alert('삭제 불가능');
            }
        }     // success
    })  // ajax


}   // bDelete













