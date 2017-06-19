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
        		if(pagina == 1){
        			$("#tabelaCargos").html('<tr><td colspan="5">Não há cargos cadastrados.</td></td>');
        		}else{
        			$("#tabelaCargos").html('<tr><td colspan="5">Não há mais cargos a serem listados.</td></td>');
        		}
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
	$("#NomeDoCargoF").val(cargo.nome);
	$("#chaveDoCargo").val(cargo.chave);
	$("#acaoFormularioCargo").val("A");
	$("#btnConfirmarCadastro").html("SALVAR");
    $('#labelNivelDeAcessoCargo').find('[value="' + n + '"]').attr('selected', true);
    $('#labelNivelDeAcessoCargo').material_select(); 
    $('#mensagemDeRetornoCargo').html("");
	$('#mensagemDeRetornoCargo').removeClass("red");
	$('#mensagemDeRetornoCargo').removeClass("green");
	$('#mensagemDeRetornoCargo').addClass("hiddendiv");
    $("#alterarDadosDoCargo").modal('open');
}

function excluirCargo(cargo){
	$("#chaveCargoAExcluir").val(cargo.chave);
	$("#labelNomeDoCargo").html(cargo.nome);
    $("#confirmarExclusaoDoCargo").modal('open');
}

function abrirFormDeCadastro(){
	$('#tituloFomunlarioCargo').html("Formulário de cadastro de cargo");
	$("#acaoFormularioCargo").val("C");
	$("#btnCancelarCadastro").trigger( "click" );
	$("#btnConfirmarCadastro").html("CADASTRAR");
	$('#mensagemDeRetornoCargo').html("");
	$('#mensagemDeRetornoCargo').removeClass("red");
	$('#mensagemDeRetornoCargo').removeClass("green");
	$('#mensagemDeRetornoCargo').addClass("hiddendiv");
	$("#alterarDadosDoCargo").modal('open');  
}

$("#formularioCadastroCargo").submit(function(event){
	var endereco;
	var form;
	var acesso = parseInt($("#labelNivelDeAcessoCargo").val());
	var nAcesso;
	var isExiste = $("#isCargoJaExiste").val();
	if(isExiste == 'F'){
		$('#mensagemDeRetornoCargo').addClass("hiddendiv");
		$('#mensagemDeRetornoCargo').removeClass("red");
		$('#mensagemDeRetornoCargo').removeClass("green");
		if(acesso == 1){
			nAcesso= "A";
		}else if(acesso == 2){
			nAcesso= "G";
		}
		if($("#acaoFormularioCargo").val() == "C"){
			form = {"nome":$("#NomeDoCargoF").val(),"nivelAcesso":nAcesso};
			endereco = "ws/cargows/cadastrar/";
		}else if($("#acaoFormularioCargo").val() == "A"){
			form = {"chave": parseInt($("#chaveDoCargo").val()),"nome":$("#NomeDoCargoF").val(),"nivelAcesso":nAcesso};
			endereco = "ws/cargows/alterar/";
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
	        			carregarCargos(paginaAtualCargos);
	        			$("#btnCancelarCadastro").trigger( "click" );
	        			$('#mensagemDeRetornoCargo').addClass("hiddendiv");
	        		},2000);
	        	}else{
	        		$('#mensagemDeRetornoCargo').html(data);
	        		$('#mensagemDeRetornoCargo').addClass("red");
	        		$('#mensagemDeRetornoCargo').removeClass("hiddendiv");
	        	}
	        },
			cache: false,
		    contentType: "application/json",
		    processData: false
		});
	}
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
        			$("#confirmarExclusaoDoCargo").modal('close');
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

function verificaSeCargoJaExiste(){
	var nome = $("#NomeDoCargoF").val();
	if($("#acaoFormularioCargo").val() == "C"){
		$.ajax({
			url: "ws/cargows/capturar/"+nome,
	        type: 'GET',
	        data: "",
	        success: function (data) {
	        	if(data == "sucess"){
	        		$('#mensagemDeRetornoCargo').html( nome.toUpperCase() +  " já está cadastrado!");
	        		$('#mensagemDeRetornoCargo').addClass("red");
	        		$('#mensagemDeRetornoCargo').removeClass("hiddendiv");
	        		$("#isCargoJaExiste").val("T");
	        	}else{
	        		$('#mensagemDeRetornoCargo').html("");
	        		$('#mensagemDeRetornoCargo').removeClass("red");
	        		$('#mensagemDeRetornoCargo').addClass("hiddendiv");
	        		$("#isCargoJaExiste").val("F");
	        	}
	        },
	        contentType: "application/json",
		});
	}
}

function recarregarCargos(){
	$("#qtdRegistrosCargos").val("10");
	$("#qtdRegistrosCargos").material_select('destroy'); 
    $("#qtdRegistrosCargos").material_select();
    carregarCargos(1);
}
