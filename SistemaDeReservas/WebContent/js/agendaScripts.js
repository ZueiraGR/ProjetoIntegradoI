var mesaSelecionada;

function abrirAgenda(mesa) {
    mesaSelecionada = mesa;
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

$("#isAtendimentoImediato").change( function (event){
    var isHorarioDefinido = document.getElementById("isAtendimentoImediato").checked;
    if(!isHorarioDefinido){
        $("#campoHoraInicio").removeClass("hiddendiv");
    }else{
        $("#campoHoraInicio").addClass("hiddendiv");
    }
});

function abrirInformacoesDaMesa(mesa){
    var html;
    html ='<div class="container">';
    html += '<h5 class="center-align mesa-label">MESA '+mesa.identificacao+' - '+mesa.quantidadeDeCadeiras+' cadeiras</h5>';
    html +='<img class="center-block responsive-img z-depth-1" src="img/'+mesa.imagem+'" alt="MESA '+mesa.identificacao+'">';
    html +='<blockquote>'+mesa.descricao+'</blockquote>';
    html +='</div>';
    $("#corpoModalMostrarDadosMesa").html(html);
    $("#corpoModalMostrarDadosMesa").modal('open');
}
