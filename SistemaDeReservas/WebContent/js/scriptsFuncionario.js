var idDivPaginacaofuncioanrio = "#OpPaginasFuncionario";
var paginaAtualFuncionarios=1;
var qtdRegistrosFuncionarios = 10;
var qtdRegistrosFuncionariosObtidos = 0;
var urlFuncionarios = "ws/funcionariows/listar/";
var urlUsuarios = "ws/funcionariows/listarUsuarios/";

/*CADASTRAR FUNCIONARIO*/

function cadastrarFuncionario(){
	$('#tituloFomunlarioFuncionario').html("Formulário de cadastro de funcionário");
	$("#hide-alterar").show();
    $("#cadastrarFuncionario").modal('open');
	selectCargos();
}

$("#CadastroDeFuncionario").submit(function(event){
	if(capturarDadosDoForm() != null){
		var formData = JSON.stringify(capturarDadosDoForm());
		$.ajax({
			url: "ws/funcionariows/cadastrar/",
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
	var nome = $("#nomeFuncionario").val();
	var sobrenome = $("#sobrenomeFuncionario").val();
	var cpf = $("#cpfFuncionario").val();
	var cargo = $("#cargoFuncionario").val();
	var login = $("#loginFuncionario").val();
	var telefone = $("#telefoneFuncionario").val();
	var email = $("#emailFuncionario").val();
	var senha = $("#senhaFuncionario").val();
	var confirmaSenha = $("#confirmaSenhaFuncionario").val();
	var funcionario;
	if(isDadosValidos(nome,sobrenome,cpf,telefone,cargo,login,email,senha,confirmaSenha)){
		funcionario = {"login":login,"senha":senha,"tipo":'F',"status":'A',"funcionario":{"nome":nome,"sobrenome":sobrenome,"cpf":cpf,"telefone":telefone,"cargo":{"chave":cargo},"email":email,"status":'A'}};
	}else{
		funcionario = null;
	}
	return funcionario;
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

function selectCargos(){
	$.ajax({
		url: "ws/cargows/listarTodos/",
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
		html += getCargo(arrayDeCargos[i]);
	}
	$("#cargoFuncionario").html(html);	
}

function getCargo(cargo){
	return '<option value="'+cargo.chave+'">'+cargo.nome+'</option>';
}

/*EXCLUIR FUNCIONARIO*/

function excluirFuncionario(chave){
	$("#confirmarExclusaoDoFuncionario").modal('open');
	$("#funcionarioID").val(chave);
}

$("#confirmarExclusaoFuncionario").submit(function(event){
	var chave = $("#funcionarioID").val();
	$.ajax({
		url: "ws/funcionariows/excluir/"+chave,
        type: 'GET',
        data: "",
        contentType: "application/json"
	});
	$("#confirmarExclusaoDoFuncionario").modal('close');
	return false;
});

/*ALTERAR DADOS DO FUNCIONARIO*/

function editarFuncionario(funcionario){
	$("#chaveFuncionarioA").val(funcionario.chave);
	$("#statusFuncionarioA").val(funcionario.status);
    $("#nomeFuncionarioA").val(funcionario.nome);
    $("#sobrenomeFuncionarioA").val(funcionario.sobrenome);
    $("#telefoneFuncionarioA").val(funcionario.telefone);
    $("#emailFuncionarioA").val(funcionario.email);
    $("#cpfFuncionarioA").val(funcionario.cpf);
    $("#alterarFuncionario").modal('open'); 
}

$("#AlterarDadosFuncionario").submit(function(event){
	if(DadosDoFormAlterar() != null){
		var formData = JSON.stringify(DadosDoFormAlterar());
		$.ajax({
			url: "ws/funcionariows/alterar/",
	        type: 'POST',
	        data: formData,
	        success: function (data) {
	        	tratarRetornoAlterar(data);
	        },
			cache: false,
		    contentType: "application/json",
		    processData: true
		});}		
	return false;
});

function DadosDoFormAlterar(){
	var chave = $("#chaveFuncionarioA").val();
	var nome = $("#nomeFuncionarioA").val();
	var sobrenome = $("#sobrenomeFuncionarioA").val();
	var cpf = $("#cpfFuncionarioA").val();
	var telefone = $("#telefoneFuncionarioA").val();
	var email = $("#emailFuncionarioA").val();
	var status = $("#statusFuncionarioA").val();
	var	funcionario = {"chave":chave,"nome":nome,"sobrenome":sobrenome,"cpf":cpf,"telefone":telefone,"email":email,"status":status};
	return funcionario;
}

/*ATIVAR/DESATIVAR FUNCIONARIO*/

function bloquearOuDesbloquearFuncionario(funcionario){
//  $("#mesagemBloqueio").html('<p class="center-align">Deseja realmente '+acao+' o cliente Maria José Vieira?</p>');
  $("#confirmarBloqueioOuDesbloqueioDoFuncionario").modal('open');   
}

/*INFORMAÇÕES DO FUNCIONARIO*/

function abrirInformacoesFuncionario(funcionario){
	$.ajax({
		url: "ws/funcionariows/capturarUsuario/"+funcionario.chave,
	    type: 'GET',
	    success: function (usuario) {
	    	$("#loginFuncionarioV").html(usuario.login);
	    },
	    data: "",
	    contentType: "application/json",
	});
	
	$("#chaveFuncionarioV").val(funcionario.chave);
	$("#statusFuncionarioV").html(funcionario.status);
    $("#nomeFuncionarioV").html(funcionario.nome);
    $("#sobrenomeFuncionarioV").html(funcionario.sobrenome);
    $("#telefoneFuncionarioV").html(funcionario.telefone);
    $("#emailFuncionarioV").html(funcionario.email);
    $("#cpfFuncionarioV").html(funcionario.cpf);
    $("#cargoFuncionarioV").html(funcionario.cargo.nome);
    $("#informacoesDoFuncionario").modal('open');    
}

/*TABELA DE FUNCIONARIOS*/

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

/*BOTÃO DE AÇÕES*/

function getAcoesFuncionario(funcionario){
	var html = getBtnInfoFuncionario(funcionario);
	html += getBtnEditarFuncionario(funcionario);
	html += getBtnAtivarInativar(funcionario);
	html += getBtnExcluirFuncionario(funcionario);
	return html;
}

function getBtnInfoFuncionario(funcionario){
	var html = "<a href='#' onclick='abrirInformacoesFuncionario("+JSON.stringify(funcionario)+")' title='Mais informações'><i class='fa fa-info-circle fa-lg blue-text text-darken-1 hoverable' aria-hidden='true'></i></a> ";
	return html;
}

function getBtnEditarFuncionario(funcionario){
	var html = "<a href='#' onclick='limparCamposFormCadastro() , editarFuncionario("+JSON.stringify(funcionario)+")' title='Editar'><i class='fa fa-pencil-square-o fa-lg indigo-text text-darken-2 hoverable' aria-hidden='true'></i></a> ";
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

/*VALIDAÇÕES E TRATAMENTOS*/

function validar(dom,tipo){
	switch(tipo){
		case'num':var regex=/[A-Za-z]|\.|\,|\;|\:|\[|\{|\]|\}|\-|\_|\=|\+|\§|\)|\(|\*|\&|\¬|\%|\¢|\$|\£|\#|\³|\@|\²|\!|\¹|\º|\ª|\°|\~|\´|\`|\>|\<|\"|\'|\\|\||\¨+/g;break;
		case'text':var regex=/\d|\.|\,|\;|\:|\[|\{|\]|\}|\-|\_|\=|\+|\§|\)|\(|\*|\&|\¬|\%|\¢|\$|\£|\#|\³|\@|\²|\!|\¹|\º|\ª|\°|\~|\´|\`|\>|\<|\"|\'|\\|\||\¨+/g;break;
	}
	dom.value=dom.value.replace(regex,'');
}

function tratarRetornoServidor(data){
	if(data == "sucess"){
		$('#mensagemRetornoCadastro').html("Cadastro realizado com sucesso!");
		$('#mensagemRetornoCadastro').addClass("green");
		$('#mensagemRetornoCadastro').removeClass("hiddendiv");
		setTimeout(function(){
			$("#cadastrarFuncionario").modal('close');
			limparCamposFormCadastro();
			$('#mensagemRetornoCadastro').addClass("hiddendiv");
			carregarFuncionarios(1);
		},2000);
	}else{
		$('#mensagemRetornoCadastro').html("Houve erro ao cadastrar!");
		$('#mensagemRetornoCadastro').addClass("red");
		$('#mensagemRetornoCadastro').removeClass("hiddendiv");
	}
}

function limparCamposFormCadastro(){
	$("#nomeFuncionario").val("");
	$("#sobrenomeFuncionario").val("");
	$("#cpfFuncionario").val("");
	$("#loginFuncionario").val("");
	$("#telefoneFuncionario").val("");
	$("#emailFuncionario").val("");
	$("#senhaFuncionario").val("");
	$("#confirmaSenhaFuncionario").val("");
	$("#cargoFuncionario").val("");
}

function formValidate(n){
	var element = '';
	const btn = document.getElementById('confirmarCadastro');
	switch (n)	{
		case 1:
			element = document.getElementById('bnome');
			if($("#nomeFuncionario").val().length < 4){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','O nome precisa ter no minimo 4 letras!')
				$('#nomeFuncionario').addClass("invalid");
				if($("#nomeFuncionario").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}
			setTimeout(function(){
				element.removeAttribute('data-balloon-visible', '')
			},3000);
			break;
		case 2:
			element = document.getElementById('bsnome');
			if($("#sobrenomeFuncionario").val().length < 4){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','O sobrenome precisa ter no minimo 4 letras!')
				$('#sobrenomeFuncionario').addClass("invalid");
				if($("#sobrenomeFuncionario").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}
			setTimeout(function(){
				element.removeAttribute('data-balloon-visible', '')
			},3000);
			break;
		case 3:
			element = document.getElementById('bcpf');
			if($("#cpfFuncionario").val().length < 11){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','Digite um cpf valido!')
				$('#cpfFuncionario').addClass("invalid");
				if($("#cpfFuncionario").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}
			setTimeout(function(){
				element.removeAttribute('data-balloon-visible', '')
			},3000);
			break;
		case 4:
			element = document.getElementById('btelefone');
			if($("#telefoneFuncionario").val().length < 11){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','Digite um telefone valido!')
				$('#telefoneFuncionario').addClass("invalid");
				if($("#telefoneFuncionario").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}
			setTimeout(function(){
				element.removeAttribute('data-balloon-visible', '')
			},3000);
			break;
		case 5:
			element = document.getElementById('blogin');
			if($("#loginFuncionario").val().length < 4){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','O sobrenome precisa ter no minimo 4 caracteres!')
				$('#loginFuncionario').addClass("invalid");
				if($("#loginFuncionario").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}
			setTimeout(function(){
				element.removeAttribute('data-balloon-visible', '')
			},3000);
			break;
		case 6:
			element = document.getElementById('bemail');
			if(validateEmail($("#emailFuncionario").val())){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','Insira um email valido!')
				$('#emailFuncionario').addClass("invalid");
				if($("#emailFuncionario").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}
			setTimeout(function(){
				element.removeAttribute('data-balloon-visible', '')
			},3000);
			break;
		case 7:
			element = document.getElementById('bsenha');
			if($("#senhaFuncionario").val().length < 8){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','A senha precisa ter no minimo 8 caracteres!')
				$('#senhaFuncionario').addClass("invalid");
				if($("#senhaFuncionario").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}
			setTimeout(function(){
				element.removeAttribute('data-balloon-visible', '')
			},3000);
			break;
		case 8:
			element = document.getElementById('bcsenha');
			if($("#confirmaSenhaFuncionario").val() != $("#senhaFuncionario").val()){
				btn.setAttribute('type','button')
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','O senha de confirmação precisa ser igual a senha!')
				$('#confirmaSenhaFuncionario').addClass("invalid");
			}else{
				btn.setAttribute('type','submit')
			}
			setTimeout(function(){
				element.removeAttribute('data-balloon-visible', '')
			},3000);
			break;			
	};
}
function validateEmail(email){
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    if(re.test(email)){
    	return false;
    }else {return true}
}

function tratarRetornoAlterar(data){
	if(data == "sucess"){
		$('#mensagemRetornoAlteracao').html("Alteração realizado com sucesso!");
		$('#mensagemRetornoAlteracao').addClass("green");
		$('#mensagemRetornoAlteracao').removeClass("hiddendiv");
		setTimeout(function(){
			$("#alterarFuncionario").modal('close');
			$('#mensagemRetornoAlteracao').addClass("hiddendiv");
			carregarFuncionarios(1);
		},2000);
	}else{
		$('#mensagemRetornoAlteracao').html("Houve erro ao alterar!");
		$('#mensagemRetornoAlteracao').addClass("red");
		$('#mensagemRetornoAlteracao').removeClass("hiddendiv");
	}
}

function formAltValidate(n){
	var element = '';
	const btn = document.getElementById('confirmarAlteracao');
	switch (n)	{
		case 1:
			element = document.getElementById('anome');
			if($("#nomeFuncionarioA").val().length < 4){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','O nome precisa ter no minimo 4 letras!')
				$('#nomeFuncionarioA').addClass("invalid");
				if($("#nomeFuncionarioA").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}
			setTimeout(function(){
				element.removeAttribute('data-balloon-visible', '')
			},3000);
			break;
		case 2:
			element = document.getElementById('asnome');
			if($("#sobrenomeFuncionarioA").val().length < 4){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','O sobrenome precisa ter no minimo 4 letras!')
				$('#sobrenomeFuncionarioA').addClass("invalid");
				if($("#sobrenomeFuncionarioA").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}
			setTimeout(function(){
				element.removeAttribute('data-balloon-visible', '')
			},3000);
			break;
		case 3:
			element = document.getElementById('acpf');
			if($("#cpfFuncionarioA").val().length < 11){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','Digite um cpf valido!')
				$('#cpfFuncionarioA').addClass("invalid");
				if($("#cpfFuncionarioA").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}
			setTimeout(function(){
				element.removeAttribute('data-balloon-visible', '')
			},3000);
			break;
		case 4:
			element = document.getElementById('atelefone');
			if($("#telefoneFuncionarioA").val().length < 11){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','Digite um telefone valido!')
				$('#telefoneFuncionarioA').addClass("invalid");
				if($("#telefoneFuncionarioA").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}
			setTimeout(function(){
				element.removeAttribute('data-balloon-visible', '')
			},3000);
			break;
		case 5:
			element = document.getElementById('aemail');
			if(validateEmail($("#emailFuncionarioA").val())){
				element.setAttribute('data-balloon-visible', '')
				element.setAttribute('data-balloon','Insira um email valido!')
				$('#emailFuncionarioA').addClass("invalid");
				if($("#emailFuncionarioA").val() == ''){element.setAttribute('data-balloon','Preenchimento deste campo é obrigatório!')}
			}
			setTimeout(function(){
				element.removeAttribute('data-balloon-visible', '')
			},3000);
			break;	
	};
}









