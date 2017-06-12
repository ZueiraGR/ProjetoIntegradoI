/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*@param {objeto[chave,identificacao,quantidadeDeCadeiras,descricao,imagem]} mesa */
$("#CadastroDeMesa").submit(function(event){
	if(capturarDadosDoForm() != null){
		var formData = JSON.stringify(capturarDadosDoForm());
		$.ajax({
			url: "ws/mesaws/cadastrar/",
	        type: 'POST',
	        data: formData,
	        /*success: function (data) {
	        	tratarRetornoServidor(data);
	        },*/
			cache: false,
		    contentType: "application/json",
		    processData: true
		});
	}
	return false;
});

function capturarDadosDoForm(){
	var identificacao = $("#identificacao").val();
	var qtd_cadeiras = $("#qtd_cadeiras").val();
	var descricao = $("#descricao").val();
	var imagem = "imagem";
	var mesa = {"identificacao":identificacao,"qtd_cadeiras":qtd_cadeiras,"descricao":descricao,"imagem":imagem};
	if(isDadosValidos()){
		return mesa; 
	}
	return mesa;
}
function isDadosValidos(identificacao,qtd_cadeiras,descricao,imagem){
	var mensagem;
	var retorno = true;
	if(identificacao == null || identificacao == ""){
		mensagem += "<li>É obrigatório preencher o campo IDENTIFICAÇÃO</li>";
		retorno = false;
	}
	if(qtd_cadeiras == null || qtd_cadeiras == ""){
		mensagem += "<li>É obrigatório preencher o campo QUANTIDADE DE CADEIRAS</li>";
		retorno = false;
	}
	return retorno;
}

function tratarRetornoServidor(data){
	if(data == "sucess"){
		$('#mensagemRetornoCadastro').html("Cadastro realizado com sucesso!");
		$('#mensagemRetornoCadastro').addClass("green");
		$('#mensagemRetornoCadastro').removeClass("hiddendiv");
		setTimeout(function(){
			$('#cadastrar').trigger("click" );
			limparCamposFormCadastro();
			$('#mensagemRetornoCadastro').addClass("hiddendiv");
		},2000);
	}else{
		$('#mensagemRetornoCadastro').html("Houve erro ao cadastrar!");
		$('#mensagemRetornoCadastro').addClass("red");
		$('#mensagemRetornoCadastro').removeClass("hiddendiv");
	}
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
