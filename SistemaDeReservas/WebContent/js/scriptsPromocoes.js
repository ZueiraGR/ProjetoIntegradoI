var idInputFilePromocao = "imagemPromocao";
var idInputHiddenPromocao = "imagemPromocaoBase64";
var idDivPaginacaoPromocoes = "#OpPaginasPromocoes";
var paginaAtualPromocoes=1;
var qtdRegistrosPromocoes = 10;
var qtdRegistrosPromocoesObtidos = 0;
var urlPromocoes = "ws/promocaows/listar/";

function carregarPromocoes(pagina){
	$("#barraCarregando").removeClass("hiddendiv");
	qtdRegistrosPromocoes = parseInt($("#qtdRegistrosPromocoes").val());
	$.ajax({
		url: "ws/promocaows/listar/"+pagina+"/"+qtdRegistrosPromocoes+"/T",
        type: 'GET',
        success: function (data) {
        	qtdRegistrosPromocoesObtidos = data.length;
        	$("#barraCarregando").addClass("hiddendiv");
        	if(data.length > 0){
        		preencherTabelaPromocoes(data);
        	}else{
        		if(pagina == 1){
        			$("#tabelaPromocoes").html('<tr><td colspan="2" class="center">Não há promoções cadastradas.</td></td>');
        		}else{
        			$("#tabelaPromocoes").html('<tr><td colspan="2" class="center">Não há mais promoções a serem listadas.</td></td>');
        		}
        	}
        }
	});
	paginaAtualPromocoes = pagina;
	listarOpPaginas(idDivPaginacaoPromocoes,paginaAtualPromocoes,qtdRegistrosPromocoes,qtdRegistrosPromocoesObtidos,"carregarPromocoes");
}

function preencherTabelaPromocoes(arrayDePromocoes){
	var html = "";
	for( i = 0; i < arrayDePromocoes.length; i++){
		html += getLinhaPromocao(arrayDePromocoes[i]);
	}
	$("#tabelaPromocoes").html(html);	
}

function getLinhaPromocao(promocao){
	linha = "<tr>";
	linha +="<td>"+promocao.nome+"</td>";
	linha +="<td>"+getAcoesPromocao(promocao)+"</td>";
	linha +="</tr>";
	return linha;
}
function getAcoesPromocao(promocao){
	var html = getBtnVisualizarPromocao(promocao);
	html += getBtnEditarPromocao(promocao);
	html += getBtnEncerrarPromoca(promocao);
	html += getBtnProrrogarPromocao(promocao);
	html += getBtnExcluirPromocao(promocao);
	return html;
}

function getBtnVisualizarPromocao(promocao){
	
}

function getBtnEncerrarPromoca(promocao){
	
}

function getBtnProrrogarPromocao(promocao){
	
}

function getBtnEditarPromocao(promocao){
	var html = "<a href='#' onclick='editarPromocao("+JSON.stringify(promocao)+")' title='Editar'><i class='fa fa-pencil-square-o fa-lg indigo-text text-darken-2 hoverable' aria-hidden='true'></i></a>";
	return html;
}

function getBtnExcluirPromocao(promocao){
	var html = "<a href='#' onclick='excluirPromocao("+JSON.stringify(promocao)+")' title='Excluir'><i class='fa fa-trash-o fa-lg orange-text text-darken-3 hoverable' aria-hidden='true'></i></a>";
	return html;
}


function editarPromocao(promocao){
	$('#tituloFomunlarioPromocao').html("Formulário de alteração de Promocao");
	$("#opcaoFormPromocoes").val("A");
	$("#btnCancelarCadastro").trigger("click");
	$("#btnConfirmarCadastro").html("SALVAR");
    $('#mensagemDeRetornoPromocao').html("");
	$('#mensagemDeRetornoPromocao').removeClass("red");
	$('#mensagemDeRetornoPromocao').removeClass("green");
	$('#mensagemDeRetornoPromocao').addClass("hiddendiv");
    $("#modalFormPromocoes").modal('open');
}

function excluirPromocao(promocao){
	$("#chavePromocaoAExcluir").val(promocao.chave);
	$("#labelNomeDoPromocao").html(promocao.nome);
    $("#confirmarExclusaoDoPromocao").modal('open');
}

function abrirFormDeCadastro(){
	$('#tituloFomunlarioPromocao').html("Formulário de cadastro de promoção");
	$("#opcaoFormPromocoes").val("C");
	$("#btnCancelarCadastro").trigger( "click" );
	$("#btnConfirmarCadastro").html("CADASTRAR");
	$('#mensagemDeRetornoPromocao').html("");
	$('#mensagemDeRetornoPromocao').removeClass("red");
	$('#mensagemDeRetornoPromocao').removeClass("green");
	$('#mensagemDeRetornoPromocao').addClass("hiddendiv");
	$("#modalFormPromocoes").modal('open');  
}

$("#modalFormPromocoes").submit(function(event){
	var endereco;
	var form;
	var isExiste = $("#isPromocaoJaExiste").val();
	var dataInicio = converteDataStringEmDate($("#dataInicio").val());
	var dataFim = converteDataStringEmDate($("#dataFim").val());
	if(isExiste == 'F'){
		$('#mensagemDeRetornoPromocao').addClass("hiddendiv");
		$('#mensagemDeRetornoPromocao').removeClass("red");
		$('#mensagemDeRetornoPromocao').removeClass("green");
		if($("#opcaoFormPromocoes").val() == "C"){
			form = { 
					"titulo":$("#titulo").val(),
					"inicio":dataInicio.getTime(),
					"fim":dataFim.getTime(),
					"descricao":$("#descricao").val(),
					"informacao":$("#informacao").val(),
					"imagem":$("#imagemPromocaoBase64").val()
			};
			
			endereco = "ws/promocaows/cadastrar/";
		}else if($("#opcaoFormPromocoes").val() == "A"){
			form = {
					"chave":$("#chavePromocao").val(),
					"titulo":$("#titulo").val(),
					"dataInicio":dataInicio.getTime(),
					"dataFim":dataFim.getTime(),
					"descricao":$("#descricao").val(),
					"informacao":$("#informacao").val(),
					"imagem":$("#imagemPromocaoBase64").val()
			};
			endereco = "ws/promocaows/alterar/";
		}
		var formData = JSON.stringify(form);
		$.ajax({
			url: endereco,
	        type: 'POST',
	        data: formData,
	        success: function (data) {
	        	if(data == "sucess"){
	        		$('#mensagemDeRetornoPromocao').html("Promoção cadastrada com sucesso!");
	        		$('#mensagemDeRetornoPromocao').addClass("green");
	        		$('#mensagemDeRetornoPromocao').removeClass("hiddendiv");
	        		setTimeout(function(){
	        			$("#alterarDadosDoPromocao").modal('close'); 
	        			carregarPromocoes(paginaAtualPromocoes);
	        			$("#btnCancelarCadastro").trigger( "click" );
	        			$('#mensagemDeRetornoPromocao').addClass("hiddendiv");
	        		},2000);
	        	}else{
	        		$('#mensagemDeRetornoPromocao').html("error");
	        		$('#mensagemDeRetornoPromocao').addClass("red");
	        		$('#mensagemDeRetornoPromocao').removeClass("hiddendiv");
	        	}
	        },
			cache: false,
		    contentType: "application/json",
		    processData: false
		});
	}
	return false;
});


$("#formExclusaoPromocao").submit(function(event){
	$('#mensagemDeRetornoExPromocao').addClass("hiddendiv");
	$('#mensagemDeRetornoExPromocao').removeClass("red");
	$('#mensagemDeRetornoExPromocao').removeClass("green");
	var chave = $("#chavePromocaoAExcluir").val();
	$.ajax({
		url: "ws/promocaows/deletar/"+chave,
        type: 'GET',
        data: "",
        success: function (data) {
        	if(data == "sucess"){
        		$('#mensagemDeRetornoExPromocao').html("Promoção excluída com sucesso!");
        		$('#mensagemDeRetornoExPromocao').addClass("green");
        		$('#mensagemDeRetornoExPromocao').removeClass("hiddendiv");
        		setTimeout(function(){
        			$("#confirmarExclusaoDoPromocao").modal('close');
        			$("#btnCancelarCadastro").trigger( "click" );
        			carregarPromocoes(paginaAtualPromocoes);
        			$('#mensagemDeRetornoExPromocao').addClass("hiddendiv");
        		},2000);
        	}else{
        		$('#mensagemDeRetornoExPromocao').html("Houve erro ao excluir a promoção!");
        		$('#mensagemDeRetornoExPromocao').addClass("green");
        		$('#mensagemDeRetornoExPromocao').removeClass("hiddendiv");
        	}
        },
        contentType: "application/json",
	});
	return false;
});

function verificaSePromocaoJaExiste(){
	var titulo = $("#NomeDoPromocaoF").val();
	if($("#acaoFormularioPromocao").val() == "C"){
		$.ajax({
			url: "ws/promocaows/capturar/"+nome,
	        type: 'GET',
	        data: "",
	        success: function (data) {
	        	if(data == "sucess"){
	        		$('#mensagemDeRetornoPromocao').html( nome.toUpperCase() +  " já está cadastrado!");
	        		$('#mensagemDeRetornoPromocao').addClass("red");
	        		$('#mensagemDeRetornoPromocao').removeClass("hiddendiv");
	        		$("#isPromocaoJaExiste").val("T");
	        	}else{
	        		$('#mensagemDeRetornoPromocao').html("");
	        		$('#mensagemDeRetornoPromocao').removeClass("red");
	        		$('#mensagemDeRetornoPromocao').addClass("hiddendiv");
	        		$("#isPromocaoJaExiste").val("F");
	        	}
	        },
	        contentType: "application/json",
		});
	}
}

function recarregarPromocoes(){
	$("#qtdRegistrosPromocoes").val("10");
	$("#qtdRegistrosPromocoes").material_select('destroy'); 
    $("#qtdRegistrosPromocoes").material_select();
    carregarPromocoes(1);
}
