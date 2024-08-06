console.log('login.js');

function login(){
console.log('login()');
let memberId= document.querySelector('.memberId').value;
let memberPw= document.querySelector('.memberPw').value;

$.ajax({
    async:false ,
    method: 'post' ,
    url: "/member/login" ,
    data:{mid : memberId ,
          mpw: memberPw
    }, 
    success: r => {
        console.log(r);
        if( r ){
            alert('로그인성공');
            location.href="/";
        }else{
            alert('로그인실패');
        }
      
    }

})






}