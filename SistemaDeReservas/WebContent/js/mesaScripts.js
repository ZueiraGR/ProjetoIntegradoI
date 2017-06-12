//TODO identificar o motivo de não setar a imagem codificada no objeto mesa 

var mesa;

$('#CadastroDeMesa').submit(function () {
	getMesa();
    if(mesa.imagem != null){
        enviarParaSevidor();
    }
    return false;
});

function getImagemMesa(){
    var file = document.getElementById('imagemMesa');
    var reader = new FileReader();
    try{
    	reader.readAsDataURL(file.files[0]);
    }catch(e){
    }
    console.log(reader.result);
    return reader.result;
}

function getMesa(){
    var identificacao = $('#numero').val();
    var cadeiras = $('#cadeiras').val();
    var descricao = $('#textarea1').val();
    var arquivoBase64 = getImagemMesa();
    try{
        mesa = { "identificacao": identificacao, "quantidadeDeCadeiras": cadeiras, "descricao": descricao, "imagem": arquivoBase64};
    }catch(e){
    }
}

function enviarParaSevidor(){
    $.ajax({
            url: "ws/mesaws/cadastrar/",
            type: 'POST',
            data: JSON.stringify(mesa),
            sucess: function (data) {
                tratarRetorno(data);
            },
            cache: false,
            contentType: "application/json",
            processData: true
        });
}
function mostrarImagemMesa(mesa) {
    var html = '<img class="responsive-img" src="img/' + mesa.imagem + '" alt="Mesa ' + mesa.identificacao + '">';
    $("#corpoMostrarImagemMesa").html(html);
    $("#modalMostrarImagemMesa").modal('open');
}

function editarMesa(mesa) {
    $("#chaveMesa").val(mesa.chave);
    $("#numeroMesa").val(mesa.identificacao);
    $("#qtdCadeirasMesa").val(mesa.qtdCadeiras);
    $("#descricaoMesa").val(mesa.descricao);
    $("#modalFormEdicaoMesa").modal('open');
}

function excluirMesa(mesa) {
    var html = '<p class="center-align">Deseja realmente excluir a mesa ' + mesa.identificacao + ' ?</p>';
    $("#corpoExcluirMesa").html(html);
    $("#modalExcluirMesa").modal('open');
}
function tratarRetorno(data) {
    if (data == "sucess") {
        $('#mensagemRetornoCadastro').html("Cadastro realizado com sucesso!");
        $('#mensagemRetornoCadastro').addClass("green");
        $('#mensagemRetornoCadastro').removeClass("hiddendiv");
        setTimeout(function () {
            $('#cadastrar').trigger("click");
            limparCamposFormCadastro();
            $('#mensagemRetornoCadastro').addClass("hiddendiv");
        }, 2000);
    } else {
        $('#mensagemRetornoCadastro').html("Houve erro ao cadastrar!");
        $('#mensagemRetornoCadastro').addClass("red");
        $('#mensagemRetornoCadastro').removeClass("hiddendiv");
    }
}
