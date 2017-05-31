var idDivPaginacaofuncioanrio = "#OpPaginasFuncionario";
var paginaAtualFuncionarios=1;
var qtdRegistrosFuncionarios = 10;
var qtdRegistrosFuncionariosObtidos = 0;
var urlFuncionarios = "ws/funcionariows/listar/";

$("#CadastroDeFuncionario").submit(function(event){
	if(capturarDadosDoForm() != null){
		var formData = JSON.stringify(capturarDadosDoForm());
		$.ajax({
			url: "ws/funcionariows/cadastrar/",
	        type: 'POST',
	        data: formData,
	        success: function (data) {
	        	if(data == "sucess"){
	        		$('#mensagemDeRetornoCargo').html("Cadastro realizado com sucesso!");
	        		$('#mensagemDeRetornoCargo').addClass("green");
	        		$('#mensagemDeRetornoCargo').removeClass("hiddendiv");
	        		setTimeout(function(){
	        			$("#alterarDadosDoCargo").modal('close'); 
	        			$("#btnCancelarCadastro").trigger( "click" );
	        			$('#mensagemDeRetornoCargo').addClass("hiddendiv");
	        		},2000);
	        	}else{
	        		$('#mensagemDeRetornoCargo').html("Houve erro ao cadastrar!");
	        		$('#mensagemDeRetornoCargo').addClass("green");
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

function capturarDadosDoForm(){
	var nome = $("#nome").val();
	var sobrenome = $("#sobrenome").val();
	var cpf = $("#cpf").val();
	var telefone = $("#telefone").val();
	var cargo = $("#cargo").val();
	var login = $("#loginC").val();
	var email = $("#email").val();
	var confirmaEmail = $("#confirmaEmail").val();
	var senha = $("#senha").val();
	var confirmaSenha = $("#confirmaSenha").val();
	var funcionario;
	if(isDadosValidos(nome,sobrenome,cpf,telefone,cargo,login,email,confirmaEmail,senha,confirmaSenha)){
		funcionario = {"login":login,"senha":senha,"tipo":'F',"status":'A',"funcionario":{"nome":nome,"sobrenome":sobrenome,"cpf":cpf,"telefone":telefone,"cargo":cargo,"email":email,"status":'A'}};
	}else{
		funcionario = null;
	}
	return funcionario;
}

function isDadosValidos(nome,sobrenome,cpf,telefone,cargo,login,email,confirmaEmail,senha,confirmaSenha){
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
	if(crgo == null || cargo == ""){
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
	if((confirmaEmail == null || confirmaEmail == "") && email != confirmaEmail){
		mensagem += "<li>É obrigatório preencher o campo CONFIRMAR EMAIL com o mesmo email informado no campo EMAIL</li>";
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


function validar(dom,tipo){
	switch(tipo){
		case'num':var regex=/[A-Za-z]/g;break;
		case'text':var regex=/\d/g;break;
	}
	dom.value=dom.value.replace(regex,'');
}

function apenasNumeros(e){
	var tecla=new Number();
	if(window.event) {
		tecla = e.keyCode;
	}
	else if(e.which) {
		tecla = e.which;
	}
	else {
		return true;
	}
	if((tecla >= "97") && (tecla <= "122")){
		return false;
	}
}

function apenasLetras(e){
	var tecla=new Number();
	if(window.event) {
		tecla = e.keyCode;
	}
	else if(e.which) {
		tecla = e.which;
	}
	else {
		return true;
	}
	if((tecla >= "48") && (tecla <= "57")){
		return false;
	}
}


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
