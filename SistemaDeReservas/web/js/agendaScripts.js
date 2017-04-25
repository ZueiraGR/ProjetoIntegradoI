var mesaSelecionada;

function abrirAgenda(id,num,nCadeiras) {
    mesaSelecionada ={chave: id, numero: num, qtdCadeiras:nCadeiras};
    $("#mesas").addClass("hiddendiv");
    $("#mudar").addClass("hiddendiv");
    $("#agenda").removeClass("hiddendiv");
    $(".fc-agendaDay-button").trigger("click");
}

function fecharAgenda() {
    $("#mesas").removeClass("hiddendiv");
    $("#mudar").removeClass("hiddendiv");
    $("#agenda").addClass("hiddendiv");
}

function confirmarAgendamento(){
    $("#agendamentoHorarioForm").submit(function(event){event.preventDefault();});
    $("#modalFormAgendamentoHorario").modal('close');
    var horario =$("#horaInicioH").val();
    var data = $("#dataReservada").val();
    var mesa = $("#mesaH").val();
    var qtdCadeiras = $("#qtdCadeirasH").val();
    Materialize.toast('Mesa ' + mesa + ' com '+ qtdCadeiras +' cadeiras reservada para o dia '+ data +' hora '+ horario, 5000);      
}

$("#agendamentoRapidoForm").submit(function (event) {
    event.preventDefault();
    $('#modalAgendamento').modal('close');
    var qtdCadeiras = $("#qtdCadeiras").val();
    Materialize.toast('Reserva realizada para a mesa 1A.' +
            '<br>Tempo de espera previsto: 15 minutos.', 4000);
});

$("#horarioDefinido").change( function (event){
    var isHorarioDefinido = document.getElementById("horarioDefinido").checked;
    if(isHorarioDefinido){
        $("#campoHoraInicio").removeClass("hiddendiv");
    }else{
        $("#campoHoraInicio").addClass("hiddendiv");
    }
});

$("#horaInicio").keypress(function (event) {
    var horaCapturada = $("#horaInicio").val();
    var hora;
    var minuto;
    var teclaPressionada = event.keyCode;
    if (horaCapturada !== null && horaCapturada !== "" && horaCapturada.length > 0) {
        if (horaCapturada.length === 2) {
            hora = parseInt(horaCapturada.substring(0, 2));
            if ((hora >= 0 && hora < 24) && teclaPressionada !== 8) {
                $("#horaInicio").val(horaCapturada + ':');
            } else {
                $("#horaInicio").val("");
                Materialize.toast('Favor preencher a hora corretamente!' +
                        '<br>O campo deve ser preenchido dentro do intervalo 00:00 até 23:59.', 4000);
            }
        }
    }
});

function abrirInformacoesDaMesa(mesa){
    var html;
    html ='<div class="container">';
    html += '<h5 class="center-align mesa-label">MESA '+mesa.numero+' - '+mesa.qtdCadeiras+' cadeiras</h5>';
    html +='<img class="center-block responsive-img z-depth-1" src="img/'+mesa.imagem+'" alt="MESA '+mesa.numero+'">';
    html +='<p>'+mesa.descricao+'</p>';
    html +='</div>';
    $("#corpoModalMostrarDadosMesa").html(html);
    $("#corpoModalMostrarDadosMesa").modal('open');
}
