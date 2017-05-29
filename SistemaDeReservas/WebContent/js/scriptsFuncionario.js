var idDivPaginacaofuncioanrio = "#OpPaginasFuncionario";
var paginaAtualFuncionarios=1;
var qtdRegistrosFuncionarios = 10;
var qtdRegistrosFuncionariosObtidos = 0;
var urlFuncionarios = "ws/funcionariows/listar/";
function carregarFuncionarios(pagina){
	$("#barraCarregando").removeClass("hiddendiv");
	qtdRegistrosFuncionarios = parseInt($("#qtdRegistrosFuncionarios").val());
	$.ajax({
		url: "ws/funcionariows/listar/"+pagina+"/"+qtdRegistrosFuncionarios,
        type: 'GET',
        success: function (data) {
        	qtdRegistrosFuncionariosObtidos = data.length;
        	$("#barraCarregando").addClass("hiddendiv");
        	if(data.length > 0){
        		preencherTabelaFuncionarios(data);
        	}else{
        		$("#tabelaFuncionarios").html("");
        	}
        }
	});
	paginaAtualFuncionarios = pagina;
	listarOpPaginas(idDivPaginacaofuncioanrio,paginaAtualFuncionarios,qtdRegistrosFuncionarios,qtdRegistrosFuncionariosObtidos,"carregarFuncionarios");
}

function preencherTabelaFuncionarios(arrayDeFuncionarios){
	var html = "";
	for( i = 0; i < arrayDeFuncionarios.length; i++){
		html += getLinhaFuncionario(arrayDeFuncionarios[i]);
	}
	$("#tabelaFuncionarios").html(html);	
}

function getLinhaFuncionario(funcionario){
	linha = "<tr>";
	linha +="<td>"+funcionario.nome+"</td>";
	linha +="<td>"+funcionario.cargo.nome+"</td>";
	linha +="<td>"+(funcionario.email == null ? "" : funcionario.email)+"</td>";
	linha +="<td>"+getAcoesFuncionario(funcionario)+"</td>";
	linha +="</tr>";
	return linha;
}
function getAcoesFuncionario(funcionario){
	var html = getBtnInfoFuncionario(funcionario);
	html += getBtnEditarFuncionario(funcionario);
	html += getBtnAtivarInativar(funcionario);
	html += getBtnExcluirFuncionario(funcionario);
	return html;
}

function getBtnInfoFuncionario(funcionario){
	var html = '<a href="#" onclick="abrirInformacoesFuncionario('+funcionario.chave+')" title="Mais informações"><i class="fa fa-info-circle fa-lg blue-text text-darken-1 hoverable" aria-hidden="true"></i></a> ';
	return html;
}

function getBtnEditarFuncionario(funcionario){
	var html = '<a href="#" onclick="editarFuncionario('+funcionario.chave+')" title="Editar"><i class="fa fa-pencil-square-o fa-lg indigo-text text-darken-2 hoverable" aria-hidden="true"></i></a> ';
	return html;
}

function getBtnAtivarInativar(funcionario){
	var html = "";
	if(funcionario.status != 'A'){
		html = '<a href="#" onclick="bloquearOuDesbloquearFuncionario('+funcionario.chave+')" title="Desbloquear"><i class="fa fa-check fa-lg green-text text-darken-1 hoverable" aria-hidden="true"></i></a> ';
	}else{
		html = '<a href="#" onclick="bloquearOuDesbloquearFuncionario('+funcionario.chave+')" title="Bloquear"><i class="fa fa-ban fa-lg red-text text-darken-1 hoverable" aria-hidden="true"></i></a> ';
	}
	return html;
}

function getBtnExcluirFuncionario(funcionario){
	var html = '<a href="#"	onclick="excluirFuncionario('+funcionario.chave+')" title="Excluir"><i class="fa fa-trash-o fa-lg orange-text text-darken-3 hoverable" aria-hidden="true"></i></a>';
	return html;
}

function abrirInformacoesFuncionario(funcionario){
    $("#informacoesDoFuncionario").modal('open');    
}

function editarFuncionario(funcionario){
    $("#alterarDadosDoFuncionario").modal('open');   
}

function bloquearOuDesbloquearFuncionario(funcionario){
//    $("#mesagemBloqueio").html('<p class="center-align">Deseja realmente '+acao+' o cliente Maria José Vieira?</p>');
    $("#confirmarBloqueioOuDesbloqueioDoFuncionario").modal('open');   
}

function excluirFuncionario(funcionario){
    $("#confirmarExclusaoDoFuncionario").modal('open');  
}
