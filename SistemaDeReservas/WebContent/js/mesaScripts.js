//TODO identificar o motivo de não setar a imagem codificada no objeto mesa 

var idInputFileMesa = "imagemMesa";
var idInputHiddenMesa = "imagemMesaBase64";
var idDivPaginacaoMesas = "#OpPaginasMesas";
var paginaAtualMesas=1;
var qtdRegistrosMesas = 10;
var qtdRegistrosMesasObtidos = 0;

function carregarMesas(pagina){
//	$("#barraCarregando").removeClass("hiddendiv");
	qtdRegistrosCargos = parseInt($("#qtdRegistrosMesas").val());
	$.ajax({
		url: "ws/mesaws/listar/"+pagina+"/"+qtdRegistrosMesas,
        type: 'GET',
        success: function (data) {
        	tratarRetornoMesas(data);
        }
	});
	paginaAtualMesas = pagina;
	listarOpPaginas(idDivPaginacaoMesas,paginaAtualMesas,qtdRegistrosMesas,qtdRegistrosMesasObtidos,"carregarMesas");
}

function tratarRetornoMesas(data){
	qtdRegistrosMesasObtidos = data.length;
	$("#barraCarregando").addClass("hiddendiv");
	if(data.length > 0){
		preencherTabelaMesas(data);
	}else{
		$("#tabelaMesas").html('<tr><td colspan="5" class="center">Não foram obtidos resultados</td></td>');
	}
}

function preencherTabelaMesas(arrayDeMesas){
	var html = "";
	for( i = 0; i < arrayDeMesas.length; i++){
		html += getLinhaMesa(arrayDeMesas[i]);
	}
	$("#tabelaMesas").html(html);
	$(document).ready(function(){
	    $('.materialboxed').materialbox();
	});
}

function getLinhaMesa(mesa){
	linha = "<tr>";
	linha +="<td>"+mesa.identificacao+"</td>";
	linha +="<td>"+mesa.quantidadeDeCadeiras+"</td>";
	linha +="<td>"+mesa.descricao+"</td>";
	linha +='<td><img class="materialboxed" src="img/'+mesa.imagem+'" data-caption="Mesa ' + mesa.identificacao + '" width="60"></td>';
	linha +="<td>"+getAcoesMesa(mesa)+"</td>";
	linha +="</tr>";
	return linha;
}
function getAcoesMesa(mesa){
	var html = getBtnEditarMesa(mesa);
	html += getBtnExcluirMesa(mesa);
	return html;
}

function getBtnEditarMesa(mesa){
	var html = "<a href='#' onclick='editarMesa("+JSON.stringify(mesa)+")' title='Editar'><i class='fa fa-pencil-square-o fa-lg indigo-text text-darken-2 hoverable' aria-hidden='true'></i></a>";
	return html;
}

function getBtnExcluirMesa(mesa){
	var html = "<a href='#' onclick='excluirMesa("+JSON.stringify(mesa)+")' title='Excluir'><i class='fa fa-trash-o fa-lg orange-text text-darken-3 hoverable' aria-hidden='true'></i></a>";
	return html;
}

function editarMesa(mesa) {
	$("#opFormMesa").val("A");
    $("#chaveMesa").val(mesa.chave);
    $("#numero").val(mesa.identificacao);
    $("#cadeiras").val(mesa.quantidadeDeCadeiras);
    $("#descricao").val(mesa.descricao);
    $("#nomeArquivoImagem").val(mesa.imagem);
    $("#labelBtnImagem").html("ALTERAR IMAGEM");
    $("#modalFormEdicaoMesa").modal('open');
}

function excluirMesa(mesa){
	$("#chaveMesaAExcluir").val(mesa.chave);
	$("#labelIdentificacaoMesa").html(mesa.identificacao);
    $("#modalExcluirMesa").modal('open');
}


$('#FormMesa').submit(function () {
	var op = $("#opFormMesa").val();
	var urlWS = "";
	var mesa = getMesa();
	if(op == "C"){
		urlWS = "ws/mesaws/cadastrar/";
	}else if(op == "A"){
		urlWS = "ws/mesaws/atualizar/";
	}
	
    if(mesa.imagem != null){
    	enviarParaFormSevidor(urlWS);
    }
    return false;
});

function getMesa(){
	var mesa;
	var chave = parseInt($("#chaveMesa").val());
    var identificacao = $('#numero').val();
    var cadeiras = $('#cadeiras').val();
    var descricao = $('#textarea1').val();
    var arquivoBase64 = $('#imagemMesaBase64').val();
    if(chave != null && chave > 0){
        mesa = { "chave": chave, "identificacao": identificacao, "quantidadeDeCadeiras": cadeiras, "descricao": descricao, "imagem": arquivoBase64};
    }else{
    	mesa = { "identificacao": identificacao, "quantidadeDeCadeiras": cadeiras, "descricao": descricao, "imagem": arquivoBase64};
    }
    return mesa;
}

function enviarParaFormSevidor(urlWS){
    $.ajax({
            url: urlWS,
            type: 'POST',
            data: JSON.stringify(getMesa()),
            sucess: function (data) {
                tratarRetorno(data);
            },
            cache: false,
            contentType: "application/json",
            processData: true
        });
}
function mostrarImagemMesa(mesa) {
    var html = '<img class="materialboxed" src="img/' + mesa.imagem + '" data-caption="Mesa ' + mesa.identificacao + ' width="250">';
    $("#corpoMostrarImagemMesa").html(html);
    $("#modalMostrarImagemMesa").modal('open');
}

function tratarRetorno(data,campoRetorno,mensagemSucess, mensagemErro) {
    if (data == "sucess") {
        $(campoRetorno).html(mensagemSucess);
        $(campoRetorno).addClass("green");
        $(campoRetorno).removeClass("hiddendiv");
        setTimeout(function () {
            $('#cadastrar').trigger("click");
            limparCamposFormCadastro();
            $(campoRetorno).addClass("hiddendiv");
        }, 2000);
    } else {
        $(campoRetorno).html(mensagemErro);
        $(campoRetorno).addClass("red");
        $(campoRetorno).removeClass("hiddendiv");
    }
}

function abrirModalCadastro(){
	$("#cancelarFormMesa").trigger("click");
	$("#opFormMesa").val("C");
	$("#labelBtnImagem").html("SELECIONAR IMAGEM");
	$("#modalFormEdicaoMesa").modal('open');
}

$("#formExclusaoMesa").submit(function(event){
	$('#mensagemDeRetornoExCargo').addClass("hiddendiv");
	$('#mensagemDeRetornoExCargo').removeClass("red");
	$('#mensagemDeRetornoExCargo').removeClass("green");
	var chave = $("#chaveMesaAExcluir").val();
	$.ajax({
		url: "ws/mesaws/excluir/"+chave,
        type: 'GET',
        data: "",
        success: function (data) {
        	tratarRetorno(data,'#mensagemDeRetornoExMesa',"Mesa excluída com sucesso!","Ocorreu um erro ao tentar excluir.");
        },
        contentType: "application/json",
	});
	return false;
});
