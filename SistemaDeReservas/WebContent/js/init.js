var eventos = [
    {
        id: 1,
        title: 'Nome do cliente: Adriano da Silva Santos \nTelefone:(61) 9999-9999',
        start: '2017-04-16T11:00:00',
        end: '2017-04-16T13:00:00'
    },
    {
        id: 2,
        title: 'Nome do cliente: Kleber Silveira Santos \nTelefone:(61) 9999-9999',
        start: '2017-04-16T13:00:00',
        end: '2017-04-16T14:00:00'
    },
    {
        id: 3,
        title: 'Nome do cliente: Maria Cristina da Silva \nTelefone:(61) 9999-9999',
        start: '2017-04-16T14:00:00',
        end: '2017-04-16T14:45:00'
    },
    {
        id: 3,
        title: 'Nome do cliente: José Antonio da Costa \nTelefone:(61) 9999-9999',
        start: '2017-04-18T19:00:00',
        end: '2017-04-18T20:30:00'
    },
    {
        title: 'Nome do cliente: Carlos Eduardo Pinheiro \nTelefone:(61) 9999-9999',
        start: '2017-04-18T20:30:00',
        end: '2017-04-18T21:30:00'
    }
];


(function ($) {
    $(function () {
        $('.button-collapse').sideNav();
    }); // end of document ready
})(jQuery); // end of jQuery name space


//inicia tabs da janela
$(document).ready(function () {
    $('ul.tabs').tabs();
});

//inicia janelas modais
$(document).ready(function () {
    $('.modal').modal();
});

//Scroll up
$(document).ready(function(){
	$(window).scroll(function(){
		if ($(this).scrollTop() > 100) {
			$('#scrollToTop').fadeIn();
		} else {
			$('#scrollToTop').fadeOut();
		}
    });
    $('#scrollToTop').click(function(){
    	$('html, body').animate({scrollTop : 0},200);
    	return false;
    }); 
});

function agendaStart(horaInicio, horaFim) {
    var initialLocaleCode = 'pt-br';

    $('#calendario').fullCalendar({
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        locale: initialLocaleCode,
        defaultView: 'agendaDay',
        defaultDate: '2017-04-16',
        timeFormat: 'H:mm',
        minTime: horaInicio,
        maxTime: horaFim,
        slotMinutes: '15',
        allDaySlot: false,
        navLinks: true, // can click day/week names to navigate views
        editable: false,
        selectable: true,
        eventLimit: true, // allow "more" link when too many events
        events: eventos,
        dayClick: function (date, jsEvent, view, resourceObj) {

            $('#calendario').fullCalendar('changeView', 'agendaDay', date);

        },
        eventClick: function (calEvent, jsEvent, view) {
            var html;
            html =  '<h5 class="center-align">DADOS DO AGENDAMENTO</h5>';
            html += '<p class="cen"><b>Cliente:</b> '+calEvent.title.substring(15,40);
            html += '<br>';
            html += '<b>Telefone:</b> (61)99999-9999';
            html += '<br>';
            html += '<b>Login:</b> teste.teste';
            html += '<br>';
            html += '<b>Mesa: </b> 1A';
            html += '<br>';
            html += '<b>Horário marcado para o inicio do atendimento:</b> '+calEvent.start.format('HH:mm');
            html += '<br>';
            html += '<b>Horário previsto para o fim do atendimento:</b> '+calEvent.end.format('HH:mm');
            html += '</p>';
            $("#corpoModalMostrarDadosAgendamento").html(html);
            $("#modalMostrarDadosAgendamento").modal('open');
        },
        select: function (time) {
            var hora = time.format('HH:mm');
            var data = time.format('DD/MM/YYYY');
            $("#horaInicioH").val(hora);
            $("#dataReservada").val(data);
            $("#chaveMesa").val(mesaSelecionada.chave);
            $("#qtdCadeirasH").val(mesaSelecionada.qtdCadeiras);
            $("#mesaH").val(mesaSelecionada.numero);
            $("#modalFormAgendamentoHorario").modal('open');
        }
    });

}
