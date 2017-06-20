var paginaAtualReservas=1;
var qtdRegistrosReservas = 10;
var qtdRegistrosReservasObtidos = 0;
var urlReservas = "ws/reservaws/listar/";
var urlUsuarios = "ws/reservaws/listarUsuarios/";

$("#CadastroDeReserva").submit(function(event){
	if(capturarDadosDoForm() != null){
		var formData = JSON.stringify(capturarDadosDoForm());
		$.ajax({
			url: "ws/reservaws/cadastrar/",
	        type: 'POST',
	        data: formData,
	        success: function (data) {
	        	tratarRetornoServidor(data);
	        },
			cache: false,
		    contentType: "application/json",
		    processData: true
		});
	}
	return false;
});

function capturarDadosDoForm(){
	var nome = $("#nomeReserva").val();
	var sobrenome = $("#sobrenomeReserva").val();
	var cpf = $("#cpfReserva").val();
	var cargo = $("#cargoReserva").val();
	var login = $("#loginReserva").val();
	var telefone = $("#telefoneReserva").val();
	var email = $("#emailReserva").val();
	var senha = $("#senhaReserva").val();
	var confirmaSenha = $("#confirmaSenhaReserva").val();
	var reserva;
	if(isDadosValidos(nome,sobrenome,cpf,telefone,cargo,login,email,senha,confirmaSenha)){
		reserva = {"login":login,"senha":senha,"tipo":'F',"status":'A',"reserva":{"nome":nome,"sobrenome":sobrenome,"cpf":cpf,"telefone":telefone,"cargo":{"chave":cargo},"email":email,"status":'A'}};
	}else{
		reserva = null;
	}
	return reserva;
}

function isDadosValidos(nome,sobrenome,cpf,telefone,cargo,login,email,senha,confirmaSenha){
	var mensagem;
//	var cpfSemFormatacao;
	var retorno = true;
	if(nome == null || nome == ""){
		mensagem += "<li>É obrigatório preencher o campo NOME</li>";
		retorno = false;
	}
	if(sobrenome == null || sobrenome == ""){
		mensagem += "<li>É obrigatório preencher o campo SOBRENOME</li>";
		retorno = false;
	}
	if(cargo == null || cargo == ""){
		mensagem += "<li>É obrigatório selecionar o campo CARGO</li>";
		retorno = false;
	}
	if(cpf == null || cpf == "" || cpf.length < 11){
		mensagem += "<li>É obrigatório preencher o campo CPF com 11 caracteres numéricos</li>";
		retorno = false;
	}
	if(login == null || login == "" || login.length < 5 ){
		mensagem += "<li>É obrigatório preencher o campo LOGIN com no mínimo 5 caracteres alfanuméricos</li>";
		retorno = false;
	}
	if(email == null || email == ""){
		mensagem += "<li>É obrigatório preencher o campo EMAIL</li>";
		retorno = false;
	}	
	if(senha == null || senha == "" || senha.length < 8){
		mensagem += "<li>É obrigatório preenche o campo SENHA com no mínimo 8 caracteres alfanuméricos</li>";
		retorno = false;
	}
	if((confirmaSenha == null || confirmaSenha == "" || confirmaSenha.length < 8) && confirmaSenha != senha){
		mensagem += "<li>É obrigatório preencher o campo CONFIRMAR SENHA com a mesma senha informada no campo SENHA</li>";
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

function limparCamposFormCadastro(){
	$("#nomeReserva").val("");
	$("#sobrenomeReserva").val("");
	$("#cpfReserva").val("");
	$("#loginReserva").val("");
	$("#telefoneReserva").val("");
	$("#emailReserva").val("");
	$("#senhaReserva").val("");
	$("#confirmaSenhaReserva").val("");
	$("#cargoReserva").val("");
}

function carregarReservas(pagina){
	$("#barraCarregando").removeClass("hiddendiv");
	qtdRegistrosReservas = parseInt($("#qtdRegistrosReservas").val());
	$.ajax({
		url: "ws/reservaws/listar/"+pagina+"/"+qtdRegistrosReservas,
        type: 'GET',
        success: function (data) {
        	qtdRegistrosReservasObtidos = data.length;
        	$("#barraCarregando").addClass("hiddendiv");
        	if(data.length > 0){
        		preencherTabelaReservas(data);
        	}else{
        		$("#tabelaReservas").html("");
        	}
        }
	});
	paginaAtualReservas = pagina;
	listarOpPaginas(idDivPaginacaofuncioanrio,paginaAtualReservas,qtdRegistrosReservas,qtdRegistrosReservasObtidos,"carregarReservas");
}

function preencherTabelaReservas(arrayDeReservas){
	var html = "";
	for( i = 0; i < arrayDeReservas.length; i++){
		html += getLinhaReserva(arrayDeReservas[i]);
	}
	$("#tabelaReservas").html(html);	
}

function getLinhaReserva(reserva){
	linha = "<tr>";
	linha +="<td>"+reserva.nome+"</td>";
	linha +="<td>"+reserva.cargo.nome+"</td>";
	linha +="<td>"+(reserva.email == null ? "" : reserva.email)+"</td>";
	linha +="<td>"+getAcoesReserva(reserva)+"</td>";
	linha +="</tr>";
	return linha;
}
function getAcoesReserva(reserva){
	var html = getBtnInfoReserva(reserva);
	html += getBtnEditarReserva(reserva);
	html += getBtnAtivarInativar(reserva);
	html += getBtnExcluirReserva(reserva);
	return html;
}

function getBtnInfoReserva(reserva){
	var html = "<a href='#' onclick='abrirInformacoesReserva("+JSON.stringify(reserva)+")' title='Mais informações'><i class='fa fa-info-circle fa-lg blue-text text-darken-1 hoverable' aria-hidden='true'></i></a> ";
	return html;
}

function getBtnEditarReserva(reserva){
	var html = "<a href='#' onclick='limparFormReserva() , editarReserva("+JSON.stringify(reserva)+")' title='Editar'><i class='fa fa-pencil-square-o fa-lg indigo-text text-darken-2 hoverable' aria-hidden='true'></i></a> ";
	return html;
}

function getBtnAtivarInativar(reserva){
	var html = "";
	if(reserva.status != 'A'){
		html = '<a href="#" onclick="bloquearOuDesbloquearReserva('+reserva.chave+')" title="Desbloquear"><i class="fa fa-check fa-lg green-text text-darken-1 hoverable" aria-hidden="true"></i></a> ';
	}else{
		html = '<a href="#" onclick="bloquearOuDesbloquearReserva('+reserva.chave+')" title="Bloquear"><i class="fa fa-ban fa-lg red-text text-darken-1 hoverable" aria-hidden="true"></i></a> ';
	}
	return html;
}

function getBtnExcluirReserva(reserva){
	var html = '<a href="#"	onclick="excluirReserva('+reserva.chave+')" title="Excluir"><i class="fa fa-trash-o fa-lg orange-text text-darken-3 hoverable" aria-hidden="true"></i></a>';
	return html;
}

function abrirInformacoesReserva(reserva){
	$.ajax({
		url: "ws/reservaws/capturarUsuario/"+reserva.chave,
	    type: 'GET',
	    success: function (usuario) {
	    	$("#loginReservaV").html(usuario.login);
	    },
	    data: "",
	    contentType: "application/json",
	});
	
	$("#chaveReservaV").val(reserva.chave);
	$("#statusReservaV").html(reserva.status);
    $("#nomeReservaV").html(reserva.nome);
    $("#sobrenomeReservaV").html(reserva.sobrenome);
    $("#telefoneReservaV").html(reserva.telefone);
    $("#emailReservaV").html(reserva.email);
    $("#cpfReservaV").html(reserva.cpf);
    $("#cargoReservaV").html(reserva.cargo.nome);
    $("#informacoesDoReserva").modal('open');    
}

function cadastrarReserva(){
	$('#tituloFomunlarioReserva').html("Formulário de cadastro de funcionário");
	$("#hide-alterar").show();
    $("#cadastrarReserva").modal('open');
	selectCargos();
}

function editarReserva(reserva){
	$("#chaveReservaA").val(reserva.chave);
	$("#statusReservaA").val(reserva.status);
    $("#nomeReservaA").val(reserva.nome);
    $("#sobrenomeReservaA").val(reserva.sobrenome);
    $("#telefoneReservaA").val(reserva.telefone);
    $("#emailReservaA").val(reserva.email);
    $("#cpfReservaA").val(reserva.cpf);
    $("#alterarReserva").modal('open'); 
}

function limparFormReserva(){
	$("#chaveReserva").val("");
    $("#nomeReserva").val("");
    $("#sobrenomeReserva").val("");
    $("#telefoneReserva").val("");
    $("#cpfReserva").val("");
    $("#emailReserva").val("");
    $("#senhaReserva").val("");
    $("#confirmaSenhaReserva").val("");
}

function bloquearOuDesbloquearReserva(reserva){
//    $("#mesagemBloqueio").html('<p class="center-align">Deseja realmente '+acao+' o cliente Maria José Vieira?</p>');
    $("#confirmarBloqueioOuDesbloqueioDoReserva").modal('open');   
}

function excluirReserva(chave){
	$("#confirmarExclusaoDoReserva").modal('open');
	$("#reservaID").val(chave);
}
$("#confirmarExclusaoReserva").submit(function(event){
	var chave = $("#reservaID").val();
	$.ajax({
		url: "ws/reservaws/excluir/"+chave,
        type: 'GET',
        data: "",
        contentType: "application/json"
	});
	$("#confirmarExclusaoDoReserva").modal('close');
	return false;
});

function selectCargos(){
	$.ajax({
		url: "ws/cargows/listarTodos/",
        type: 'GET',
        success: function (data) {
        	$('select').material_select('destroy');
        	if(data.length > 0){
        		preencherSelectCargos(data);
        	}else{
        		$("#cargoReserva").html("");
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
		html += getCargo(arrayDeCargos[i]);
	}
	$("#cargoReserva").html(html);	
}

function getCargo(cargo){
	return '<option value="'+cargo.chave+'">'+cargo.nome+'</option>';
}

$("#AlterarDadosReserva").submit(function(event){
	if(DadosDoFormAlterar() != null){
		var formData = JSON.stringify(DadosDoFormAlterar());
		$.ajax({
			url: "ws/reservaws/alterar/",
	        type: 'POST',
	        data: formData,
			cache: false,
		    contentType: "application/json",
		    processData: true
		});}		
	return false;
});

function DadosDoFormAlterar(){
	var chave = $("#chaveReservaA").val();
	var nome = $("#nomeReservaA").val();
	var sobrenome = $("#sobrenomeReservaA").val();
	var cpf = $("#cpfReservaA").val();
	var telefone = $("#telefoneReservaA").val();
	var email = $("#emailReservaA").val();
	var status = $("#statusReservaA").val();
	var	reserva = {"chave":chave,"nome":nome,"sobrenome":sobrenome,"cpf":cpf,"telefone":telefone,"email":email,"status":status};
	return reserva;
}


