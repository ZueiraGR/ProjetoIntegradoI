/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*@param {objeto[chave,identificacao,qtdCadeiras,descricao,imagem]} mesa */
function mostrarImagemMesa(mesa){
    var html = '<img class="responsive-img" src="img/'+mesa.imagem+'" alt="Mesa '+mesa.identificacao+'">';
    $("#corpoMostrarImagemMesa").html(html);
    $("#modalMostrarImagemMesa").modal('open');
}

function editarMesa(mesa){
    $("#chaveMesa").val(mesa.chave);
    $("#numeroMesa").val(mesa.identificacao);
    $("#qtdCadeirasMesa").val(mesa.qtdCadeiras);
    $("#descricaoMesa").val(mesa.descricao);
    $("#modalFormEdicaoMesa").modal('open');
}

function excluirMesa(mesa){
    var html = '<p class="center-align">Deseja realmente excluir a mesa '+mesa.identificacao+' ?</p>';
    $("#corpoExcluirMesa").html(html);
    $("#modalExcluirMesa").modal('open');
}
