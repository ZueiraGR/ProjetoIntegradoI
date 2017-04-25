/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function abrirInformacoes(){
    $("#informacoesCliente").modal('open');    
}

function editar(){
    $("#alterardados").modal('open');   
}

function bloquearOuDesbloquear(acao){
    $("#mesagemBloqueio").html('<p class="center-align">Deseja realmente '+acao+' o cliente Maria José Vieira?</p>');
    $("#confirmarBloqueioOuDesbloqueio").modal('open');   
}

function excluir(){
    $("#confirmarExclusao").modal('open');  
}
