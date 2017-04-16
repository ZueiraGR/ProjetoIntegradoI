function abrirAgenda(chaveMesa) {
    $("#mesas").addClass("hiddendiv");
    $("#agenda").removeClass("hiddendiv");
    $(".fc-agendaDay-button").trigger("click");
}

function fecharAgenda() {
    $("#mesas").removeClass("hiddendiv");
    $("#agenda").addClass("hiddendiv");
}

function adicionarEventoNaAgenda(titulo, inicio, fim) {

}


$("#agendamentoRapidoForm").submit(function (event) {
    event.preventDefault();
    $('#modalAgendamento').modal('close');
    var qtdCadeiras = $("#qtdCadeiras").val();
    Materialize.toast('Reserva realizada para a mesa 1.' +
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

function validaHora(){
    var horaCapturada = $("#horaInicio").val();
    var hora;
    var minuto;
    if (horaCapturada !== null && horaCapturada !== "" && horaCapturada.length > 0) {
        if (horaCapturada.length === 5) {
            hora = parseInt(horaCapturada.substring(0, 2));
            minuto = parseInt(horaCapturada.substring(3, 5));
            if (!(hora >= 0 && hora < 24)) {
                $("#horaInicio").val("");
                $("#horaInicio").focus();
                Materialize.toast('Favor preencher a hora corretamente!' +
                        '<br>O campo deve ser preenchido dentro do intervalo 00:00 até 23:59.', 4000);
            }
            if (!(minuto > 0 && minuto < 60)) {
                $("#horaInicio").val("");
                $("#horaInicio").focus();
                Materialize.toast('Favor preencher a hora corretamente!' +
                        '<br>O campo deve ser preenchido dentro do intervalo 00:00 até 23:59.', 4000);
            }
        }
    }
}

