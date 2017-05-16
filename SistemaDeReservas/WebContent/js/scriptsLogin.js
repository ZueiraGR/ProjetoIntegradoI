var LOGIN_INVALIDO= "Login inválido.";
var SENHA_INCORRETA = "Senha Incorreta";

$('#formularioDeLogin').submit(function (event) {
	//event.preventDefault();
	var formData = $('#formularioDeLogin').serialize();
    $.ajax({
        url: "autenticar.do",
        type: 'POST',
        data: formData,
        success: function (data) {
            if(data.loginValido==0 && data.senhaValida==0){
	            alert(LOGIN_INVALIDO);
            }else if(data.loginValido==1 && data.senhaValida==0){
            	alert(SENHA_INCORRETA);
            }else if(data.loginValido==1 && data.senhaValida==1){
            	setTimeout(entrar,3000);
            }
        },
        cache: false,
        contentType: false,
        processData: false
    });
    return false;
});

function entrar(){
    location.href='reserva.do';
}