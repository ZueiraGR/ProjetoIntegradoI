/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*@param {objeto[chave,identificacao,qtdCadeiras,descricao,imagem]} mesa */
$("#CadastroDeMesa").submit(function(event){
	if(DadosDoForm() != null){
		var formData = JSON.stringify(capturarDadosDoForm());
		$.ajax({
			url: "ws/mesaws/cadastrar/",
	        type: 'POST',
	        data: formData,
			cache: false,
		    contentType: "application/json",
		    processData: true
		});
	}
	return false;
});

function DadosDoForm(){
	var numero = $("#numero").val();
	var cadeiras = $("#cadeiras").val();
	var descricao = $("#textarea1").val();
}



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
