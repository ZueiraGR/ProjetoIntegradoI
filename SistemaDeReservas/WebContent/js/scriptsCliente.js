var idDivPaginacaocliente = "#OpPaginasCliente";
var paginaAtualClientes = 1;
var qtdRegistrosClientes = 10;
var qtdRegistrosClientesObtidos = 0;
var urlClientes = "ws/clientews/listar/";

/* CADASTRAR CLIENTE */

function cadastrarCliente() {
	$('#tituloFomunlarioCliente').html("Formulário de cadastro de cliente");
	$("#cadastrarCliente").modal('open');
}

$("#formularioDeCadastro").submit(function(event) {
	if (capturarDadosDoForm() != null) {
		var formData = JSON.stringify(capturarDadosDoForm());
		$.ajax({
			url : "ws/clientews/cadastrar/",
			type : 'POST',
			data : formData,
			success : function(data) {
				tratarRetornoServidor(data);
			},
			cache : false,
			contentType : "application/json",
			processData : true
		});
	}
	return false;
});

function capturarDadosDoForm() {
	var nome = $("#nome").val();
	var sobrenome = $("#sobrenome").val();
	var cpf = $("#cpf").val();
	var telefone = $("#telefone").val();
	var login = $("#loginC").val();
	var email = $("#email").val();
	var senha = $("#senha").val();
	var confirmaSenha = $("#confirmaSenha").val();
	var cliente;
	if (isDadosValidos(nome, sobrenome, cpf, telefone, login, email, senha, confirmaSenha)) {
		cliente = {
			"login" : login,
			"senha" : senha,
			"tipo" : 'C',
			"status" : 'A',
			"cliente" : {
				"nome" : nome,
				"sobrenome" : sobrenome,
				"cpf" : cpf,
				"telefone" : telefone,
				"email" : email,
				"status" : 'A'
			}
		};
	} else {
		cliente = null;
	}
	return cliente;
}

function isDadosValidos(nome, sobrenome, cpf, telefone, login, email, senha, confirmaSenha) {
	var mensagem;
	// var cpfSemFormatacao;
	var retorno = true;
	if (nome == null || nome == "") {
		mensagem += "<li>É obrigatório preencher o campo NOME</li>";
		retorno = false;
	}
	if (sobrenome == null || sobrenome == "") {
		mensagem += "<li>É obrigatório preencher o campo SOBRENOME</li>";
		retorno = false;
	}
	if (cpf == null || cpf == "" || cpf.length < 11) {
		mensagem += "<li>É obrigatório preencher o campo CPF com 11 caracteres numéricos</li>";
		retorno = false;
	}
	if (login == null || login == "" || login.length < 5) {
		mensagem += "<li>É obrigatório preencher o campo LOGIN com no mínimo 5 caracteres alfanuméricos</li>";
		retorno = false;
	}
	if (email == null || email == "") {
		mensagem += "<li>É obrigatório preencher o campo EMAIL</li>";
		retorno = false;
	}
	if (senha == null || senha == "" || senha.length < 8) {
		mensagem += "<li>É obrigatório preenche o campo SENHA com no mínimo 8 caracteres alfanuméricos</li>";
		retorno = false;
	}
	if ((confirmaSenha == null || confirmaSenha == "" || confirmaSenha.length < 8)
			&& confirmaSenha != senha) {
		mensagem += "<li>É obrigatório preencher o campo CONFIRMAR SENHA com a mesma senha informada no campo SENHA</li>";
		retorno = false;
	}
	return retorno;
}

/* EXCLUIR CLIENTE */

function excluirCliente(chave) {
	$("#confirmarExclusaoDoCliente").modal('open');
	$("#clienteID").val(chave);
}

$("#confirmarExclusaoCliente").submit(function(event) {
	var chave = $("#clienteID").val();
	$.ajax({
		url : "ws/clientews/excluir/" + chave,
		type : 'GET',
		data : "",
		contentType : "application/json"
	});
	$("#confirmarExclusaoDoCliente").modal('close');
	return false;
});

/* ALTERAR DADOS DO CLIENTE */

function editarCliente(cliente) {
	$("#chaveClienteA").val(cliente.chave);
	$("#statusClienteA").val(cliente.status);
	$("#nomeClienteA").val(cliente.nome);
	$("#sobrenomeClienteA").val(cliente.sobrenome);
	$("#telefoneClienteA").val(cliente.telefone);
	$("#emailClienteA").val(cliente.email);
	$("#cpfClienteA").val(cliente.cpf);
	$("#alterarCliente").modal('open');
}

$("#AlterarDadosCliente").submit(function(event) {
	if (DadosDoFormAlterar() != null) {
		var formData = JSON.stringify(DadosDoFormAlterar());
		$.ajax({
			url : "ws/clientews/alterar/",
			type : 'POST',
			data : formData,
			cache : false,
			contentType : "application/json",
			processData : true
		});
	}
	return false;
});

function DadosDoFormAlterar() {
	var chave = $("#chaveClienteA").val();
	var nome = $("#nomeClienteA").val();
	var sobrenome = $("#sobrenomeClienteA").val();
	var cpf = $("#cpfClienteA").val();
	var telefone = $("#telefoneClienteA").val();
	var email = $("#emailClienteA").val();
	var status = $("#statusClienteA").val();
	var cliente = {
		"chave" : chave,
		"nome" : nome,
		"sobrenome" : sobrenome,
		"cpf" : cpf,
		"telefone" : telefone,
		"email" : email,
		"status" : status
	};
	return cliente;
}

/* ATIVAR/DESATIVAR CLIENTE */

function bloquearOuDesbloquearCliente(cliente) {
	$("#confirmarBloqueioOuDesbloqueioDoCliente").modal('open');
}

/* INFORMAÇÕES DO CLIENTE */

function abrirInformacoesCliente(cliente) {
	$.ajax({
		url : "ws/clientews/capturarUsuario/" + cliente.chave,
		type : 'GET',
		success : function(usuario) {
			$("#loginClienteV").html(usuario.login);
		},
		data : "",
		contentType : "application/json",
	});
	$("#chaveClienteV").val(cliente.chave);
	$("#statusClienteV").html(cliente.status);
	$("#nomeClienteV").html(cliente.nome);
	$("#sobrenomeClienteV").html(cliente.sobrenome);
	$("#telefoneClienteV").html(cliente.telefone);
	$("#emailClienteV").html(cliente.email);
	$("#cpfClienteV").html(cliente.cpf);
	$("#informacoesDoCliente").modal('open');
}

/* TABELA DE CLIENTES */

function carregarClientes(pagina) {
	qtdRegistrosClientes = parseInt($("#qtdRegistrosClientes").val());
	$.ajax({
		url : "ws/clientews/listar/" + pagina + "/" + qtdRegistrosClientes,
		type : 'GET',
		success : function(data) {
			qtdRegistrosClientesObtidos = data.length;
			if (data.length > 0) {
				preencherTabelaClientes(data);
			} else {
				$("#tabelaClientes").html("");
			}
		}
	});
	paginaAtualClientes = pagina;
	listarOpPaginas(idDivPaginacaocliente, paginaAtualClientes,
			qtdRegistrosClientes, qtdRegistrosClientesObtidos,
			"carregarClientes");
}

function preencherTabelaClientes(arrayDeClientes) {
	var html = "";
	for (i = 0; i < arrayDeClientes.length; i++) {
		html += getLinhaCliente(arrayDeClientes[i]);
	}
	$("#tabelaClientes").html(html);
}

function getLinhaCliente(cliente) {
	linha = "<tr>";
	linha += "<td>" + cliente.nome + "</td>";
	linha += "<td>" + cliente.sobrenome + "</td>";
	linha += "<td>" + cliente.cpf + "</td>";
	linha += "<td>" + (cliente.email == null ? "" : cliente.email) + "</td>";
	linha += "<td>" + cliente.telefone + "</td>";
	linha += "<td>" + getAcoesCliente(cliente) + "</td>";
	linha += "</tr>";
	return linha;
}

/* BOTÃO DE AÇÕES */

function getAcoesCliente(cliente) {
	var html = getBtnInfoCliente(cliente);
	html += getBtnEditarCliente(cliente);
	html += getBtnAtivarCliente(cliente);
	html += getBtnExcluirCliente(cliente);
	return html;
}

function getBtnInfoCliente(cliente) {
	var html = "<a href='#' onclick='abrirInformacoesCliente("
			+ JSON.stringify(cliente)
			+ ")' title='Mais informações'><i class='fa fa-info-circle fa-lg blue-text text-darken-1 hoverable' aria-hidden='true'></i></a> ";
	return html;
}

function getBtnEditarCliente(cliente) {
	var html = "<a href='#' onclick='limparCamposFormCadastro() , editarCliente("
			+ JSON.stringify(cliente)
			+ ")' title='Editar'><i class='fa fa-pencil-square-o fa-lg indigo-text text-darken-2 hoverable' aria-hidden='true'></i></a> ";
	return html;
}

function getBtnAtivarCliente(cliente) {
	var html = "";
	if (cliente.status != 'A') {
		html = '<a href="#" onclick="bloquearOuDesbloquearCliente('
				+ cliente.chave
				+ ')" title="Desbloquear"><i class="fa fa-check fa-lg green-text text-darken-1 hoverable" aria-hidden="true"></i></a> ';
	} else {
		html = '<a href="#" onclick="bloquearOuDesbloquearCliente('
				+ cliente.chave
				+ ')" title="Bloquear"><i class="fa fa-ban fa-lg red-text text-darken-1 hoverable" aria-hidden="true"></i></a> ';
	}
	return html;
}

function getBtnExcluirCliente(cliente) {
	var html = '<a href="#"	onclick="excluirCliente('
			+ cliente.chave
			+ ')" title="Excluir"><i class="fa fa-trash-o fa-lg orange-text text-darken-3 hoverable" aria-hidden="true"></i></a>';
	return html;
}

/* VALIDAÇÕES E TRATAMENTOS */

function validar(dom, tipo) {
	switch (tipo) {
		case'num':var regex=/[A-Za-z]|\.|\,|\;|\:|\[|\{|\]|\}|\-|\_|\=|\+|\§|\)|\(|\*|\&|\¬|\%|\¢|\$|\£|\#|\³|\@|\²|\!|\¹|\º|\ª|\°|\~|\´|\`|\>|\<|\"|\'|\\|\||\¨+/g;break;
		case'text':var regex=/\d|\.|\,|\;|\:|\[|\{|\]|\}|\-|\_|\=|\+|\§|\)|\(|\*|\&|\¬|\%|\¢|\$|\£|\#|\³|\@|\²|\!|\¹|\º|\ª|\°|\~|\´|\`|\>|\<|\"|\'|\\|\||\¨+/g;break;
	}
	dom.value = dom.value.replace(regex, '');
}

function apenasNumeros(e) {
	var tecla = new Number();
	if (window.event) {
		tecla = e.keyCode;
	} else if (e.which) {
		tecla = e.which;
	} else {
		return true;
	}
	if ((tecla >= "97") && (tecla <= "122")) {
		return false;
	}
}

function apenasLetras(e) {
	var tecla = new Number();
	if (window.event) {
		tecla = e.keyCode;
	} else if (e.which) {
		tecla = e.which;
	} else {
		return true;
	}
	if ((tecla >= "48") && (tecla <= "57")) {
		return false;
	}
}

function tratarRetornoServidor(data) {
	if (data == "sucess") {
		$('#mensagemRetornoCadastro').html("Cadastro realizado com sucesso!");
		$('#mensagemRetornoCadastro').addClass("green");
		$('#mensagemRetornoCadastro').removeClass("hiddendiv");
		setTimeout(function() {
			$('#btnAcaoFormLogin').trigger("click");
			limparCamposFormCadastro();
			$('#mensagemRetornoCadastro').addClass("hiddendiv");
		}, 2000);
	} else {
		$('#mensagemRetornoCadastro').html("Houve erro ao cadastrar!");
		$('#mensagemRetornoCadastro').addClass("red");
		$('#mensagemRetornoCadastro').removeClass("hiddendiv");
	}
}

function limparCamposFormCadastro() {
	$("#nome").val("");
	$("#sobrenome").val("");
	$("#cpf").val("");
	$("#telefone").val("");
	$("#loginC").val("");
	$("#email").val("");
	$("#confirmaEmail").val("");
	$("#senha").val("");
	$("#confirmaSenha").val("");
}

function formValidate(n){
	var element = '';
	const btn = document.getElementById('confirmarCadastro');
	switch (n)	{
		case 1:
			element = document.getElementById('bnome');
			if($("#nome").val().length < 4){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','O nome precisa ter no minimo 4 letras!')
				if($("#nome").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}else{
				element.removeAttribute('data-balloon-visible', '')
			}
			break;
		case 2:
			element = document.getElementById('bsnome');
			if($("#sobrenome").val().length < 4){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','O sobrenome precisa ter no minimo 4 letras!')
				if($("#sobrenome").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}else{
				element.removeAttribute('data-balloon-visible', '')
			}
			break;
		case 3:
			element = document.getElementById('bcpf');
			if($("#cpf").val().length < 11){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','Digite um cpf valido!')
				if($("#cpf").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}else{
				element.removeAttribute('data-balloon-visible', '')
			}
			break;
		case 4:
			element = document.getElementById('btelefone');
			if($("#telefone").val().length < 11){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','Digite um telefone valido!')
				if($("#telefone").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}else{
				element.removeAttribute('data-balloon-visible', '')
			}
			break;
		case 5:
			element = document.getElementById('blogin');
			if($("#loginC").val().length < 4){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','O sobrenome precisa ter no minimo 4 caracteres!')
				if($("#loginC").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}else{
				element.removeAttribute('data-balloon-visible', '')
			}
			break;
		case 6:
			element = document.getElementById('bemail');
			if(validateEmail($("#email").val())){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','Insira um email valido!')
				if($("#email").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}else{
				element.removeAttribute('data-balloon-visible', '')
			}
			break;
		case 7:
			element = document.getElementById('bsenha');
			if($("#senha").val().length < 8){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','A senha precisa ter no minimo 8 caracteres!')
				if($("#senha").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}else{
				element.removeAttribute('data-balloon-visible', '')
			}
			break;
		case 8:
			element = document.getElementById('bcsenha');
			if($("#confirmaSenha").val() != $("#senha").val()){
				btn.setAttribute('type','button')
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','O senha de confirmação precisa ser igual a senha!')
			}else{
				element.removeAttribute('data-balloon-visible', '')
				btn.setAttribute('type','submit')
			}
			break;			
	};
}
function validateEmail(email){
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(re.test(email)){
    	return false;
    }else {return true}
}

