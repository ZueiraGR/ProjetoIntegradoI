/**
 * Cadastro de clinetes
 */
$S("#formularioDeCadastro").submit(function(event){
	
});

function capturarDadosDoForm(){
	var nome = $("#nome").val();
	var sobrenome = $("#sobrenome").val();
	var cpf = $("#cpf").val();
	var login = $("#login").val();
	var email = $("#email").val();
	var confirmaEmail = $("#confirmaEmail").val();
	var senha = $("#senha").val();
	var confirmaSenha = $("#confirmaSenha").val();
	var cliente;
	if(isDadosVálidos(nome,sobrenome,cpf,login,email,confirmaEmail,senha,confirmaSenha)){
		cliente = {"nome":nome,"sobrenome":sobrenome,"cpf":cpf,"email":email,"senha":senha};
	}else{
		cliente = null;
	}
	return cliente;
}

function isDadosVálidos(nome,sobrenome,cpf,login,email,confirmaEmail,senha,confirmaSenha){
	
}