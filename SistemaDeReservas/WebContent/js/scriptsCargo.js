var idDivPaginacaoCargos = "#OpPaginasCargo";
var paginaAtualCargos=1;
var qtdRegistrosCargos = 10;
var qtdRegistrosCargosObtidos = 0;
var urlCargos = "ws/cargows/listar/";
function carregarCargos(pagina){
	$("#barraCarregando").removeClass("hiddendiv");
	qtdRegistrosCargos = parseInt($("#qtdRegistrosCargos").val());
	$.ajax({
		url: "ws/cargows/listar/"+pagina+"/"+qtdRegistrosCargos,
        type: 'GET',
        success: function (data) {
        	qtdRegistrosCargosObtidos = data.length;
        	$("#barraCarregando").addClass("hiddendiv");
        	if(data.length > 0){
        		preencherTabelaCargos(data);
        	}else{
        		$("#tabelaCargos").html('<tr><td colspan="5">Não há novos registros.</td></td>');
        	}
        }
	});
	paginaAtualCargos = pagina;
	listarOpPaginas(idDivPaginacaoCargos,paginaAtualCargos,qtdRegistrosCargos,qtdRegistrosCargosObtidos,"carregarCargos");
}

function preencherTabelaCargos(arrayDeCargos){
	var html = "";
	for( i = 0; i < arrayDeCargos.length; i++){
		html += getLinhaCargo(arrayDeCargos[i]);
	}
	$("#tabelaCargos").html(html);	
}

function getLinhaCargo(cargo){
	linha = "<tr>";
	linha +="<td>"+cargo.nome+"</td>";
	linha +="<td>"+(cargo.nivelAcesso == 'A' ? "Atendimento" : "Gerenciamento")+"</td>";
	linha +="<td>"+getAcoesCargo(cargo)+"</td>";
	linha +="</tr>";
	return linha;
}
function getAcoesCargo(cargo){
	var html = getBtnEditarCargo(cargo);
	html += getBtnExcluirCargo(cargo);
	return html;
}

function getBtnEditarCargo(cargo){
	var html = "<a href='#' onclick='editarCargo("+JSON.stringify(cargo)+")' title='Editar'><i class='fa fa-pencil-square-o fa-lg indigo-text text-darken-2 hoverable' aria-hidden='true'></i></a>";
	return html;
}

function getBtnExcluirCargo(cargo){
	var html = "<a href='#' onclick='excluirCargo("+JSON.stringify(cargo)+")' title='Excluir'><i class='fa fa-trash-o fa-lg orange-text text-darken-3 hoverable' aria-hidden='true'></i></a>";
	return html;
}


function editarCargo(cargo){
	var n = (cargo.nivelAcesso == 'A' ? 1 : 2);
	$('#tituloFomunlarioCargo').html("Formulário de alteração de Cargo");
	$('#labelNivelDeAcessoCargo').find('[selected="selected"]').removeAttr("selected");
	$('#labelNivelDeAcessoCargo').material_select('destroy');
	$("#btnCancelarCadastro").trigger("click");
	$("#labelNomeDoCargoF").val(cargo.nome);
	$("#chaveDoCargoF").val(cargo.chave);
	$("#acaoFormularioCargo").val("A");
	$("#btnConfirmarCadastro").html("SALVAR");
    $('#labelNivelDeAcessoCargo').find('[value="' + n + '"]').attr('selected', true);
    $('#labelNivelDeAcessoCargo').material_select(); 
    $("#alterarDadosDoCargo").modal('open');
}

function excluirCargo(cargo){
	$("#chaveCargoAExcluir").val(cargo.chave);
	$("#labelNomeDoCargo").html(cargo.nome);
    $("#confirmarExclusaoDoCargo").modal('open');
}

function abrirFormDeCadastro(){
	$('#tituloFomunlarioCargo').html("Formulário de cadastro de Cargo");
	$("#acaoFormularioCargo").val("C");
	$("#btnCancelarCadastro").trigger( "click" );
	$("#btnConfirmarCadastro").html("CADASTRAR");
	$("#alterarDadosDoCargo").modal('open');  
}

$("#formularioCadastroCargo").submit(function(event){
	$('#mensagemDeRetornoCargo').addClass("hiddendiv");
	$('#mensagemDeRetornoCargo').removeClass("red");
	$('#mensagemDeRetornoCargo').removeClass("green");
	var endereco;
	var form;
	var acesso = parseInt($("#labelNivelDeAcessoCargo").val());
	var nAcesso;
	if(acesso == 1){
		nAcesso= "A";
	}else if(acesso == 2){
		nAcesso= "G";
	}
	if($("#acaoFormularioCargo").val() == "C"){
		form = {"nome":$("#labelNomeDoCargoF").val(),"nivelAcesso":nAcesso};
		endereco = "ws/cargows/cadastrar/";
	}else if($("#acaoFormularioCargo").val() == "A"){
		form = {"nome":$("#labelNomeDoCargoF").val(),"nivelAcesso":nAcesso};
		endereco = "ws/cargows/alterar/"+$("#chaveDoCargo").val();
	}
	var formData = JSON.stringify(form);	
	$.ajax({
		url: endereco,
        type: 'POST',
        data: formData,
        success: function (data) {
        	if(data == "sucess"){
        		$('#mensagemDeRetornoCargo').html("Cargo cadastrado com sucesso!");
        		$('#mensagemDeRetornoCargo').addClass("green");
        		$('#mensagemDeRetornoCargo').removeClass("hiddendiv");
        		setTimeout(function(){
        			$("#alterarDadosDoCargo").modal('close'); 
        			$("#btnCancelarCadastro").trigger( "click" );
        			$('#mensagemDeRetornoCargo').addClass("hiddendiv");
        		},2000);
        	}else{
        		$('#mensagemDeRetornoCargo').html("Houve erro ao cadastrar o cargo!");
        		$('#mensagemDeRetornoCargo').addClass("red");
        		$('#mensagemDeRetornoCargo').removeClass("hiddendiv");
        	}
        },
		cache: false,
	    contentType: "application/json",
	    processData: false
	});
	return false;
});


$("#formExclusaoCargo").submit(function(event){
	$('#mensagemDeRetornoExCargo').addClass("hiddendiv");
	$('#mensagemDeRetornoExCargo').removeClass("red");
	$('#mensagemDeRetornoExCargo').removeClass("green");
	var chave = $("#chaveCargoAExcluir").val();
	$.ajax({
		url: "ws/cargows/deletar/"+chave,
        type: 'GET',
        data: "",
        success: function (data) {
        	if(data == "sucess"){
        		$('#mensagemDeRetornoExCargo').html("Cargo excluído com sucesso!");
        		$('#mensagemDeRetornoExCargo').addClass("green");
        		$('#mensagemDeRetornoExCargo').removeClass("hiddendiv");
        		setTimeout(function(){
        			$("#alterarDadosDoCargo").modal('close'); 
        			$("#btnCancelarCadastro").trigger( "click" );
        			carregarCargos(paginaAtualCargos);
        			$('#mensagemDeRetornoExCargo').addClass("hiddendiv");
        		},2000);
        	}else{
        		$('#mensagemDeRetornoExCargo').html("Houve erro ao excluir o cargo!");
        		$('#mensagemDeRetornoExCargo').addClass("green");
        		$('#mensagemDeRetornoExCargo').removeClass("hiddendiv");
        	}
        },
        contentType: "application/json",
	});
	return false;
});


function selectCargos(){
	$.ajax({
		url: "ws/cargows/listar/1/10",
        type: 'GET',
        success: function (data) {
        	$('select').material_select('destroy');
        	if(data.length > 0){
        		preencherSelectCargos(data);
        	}else{
        		$("#cargoFuncionario").html("");
        	}
        	$(document).ready(function(){
        		$('select').material_select();
        	})
        }
	});
}

function preencherSelectCargos(arrayDeCargos){
	var html = '<option disabled="disabled" selected="selected">Selecione um cargo</option>';
	for( i = 0; i < arrayDeCargos.length; i++){
		html += getLinhaCargo(arrayDeCargos[i]);
	}
	$("#cargoFuncionario").html(html);	
}

function getLinhaCargo(cargo){
	return '<option value="'+cargo.chave+'">'+cargo.nome+'</option>';
}
