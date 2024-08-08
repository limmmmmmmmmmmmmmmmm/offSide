console.log('mprint.js');

mprint();
function mprint(){  console.log('mprint()');

    // 어디에
    let memberinfoBox= document.querySelector('#memberinfoBox');

    // 무엇을
    let = html = ``;

    $.ajax({
        async:false ,
        method: 'get' ,
        url: "/admin/mprint" ,
        success: result => {console.log(result)

            result.forEach( m => {
                html += `   <tr>
                                <td> ${m.mid} </td>
                                <td> ${m.mname} </td>
                                <td> ${m.mphone} </td>
                                <td> ${m.mgender} </td>
                                <td> ${m.mbirth} </td>
                                <td> ${m.maccount} </td>
                            </tr>
                        `

            })

        }
    })
    memberinfoBox.innerHTML = html;
}