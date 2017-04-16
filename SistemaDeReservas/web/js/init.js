var eventos = [
    {
        id: 1,
        title: 'Adriano',
        start: '2017-04-16T11:00:00',
        end: '2017-04-16T13:00:00'
    },
    {
        id: 2,
        title: 'Kleber',
        start: '2017-04-16T13:00:00',
        end: '2017-04-16T14:00:00'
    },
    {
        id: 3,
        title: 'Maria',
        start: '2017-04-16T14:00:00',
        end: '2017-04-16T14:30:00'
    },
    {
        id: 3,
        title: 'Maria',
        start: '2017-04-18T19:00:00',
        end: '2017-04-18T20:30:00'
    },
    {
        title: 'Click for Google',
        url: 'http://google.com/',
        start: '2017-04-28'
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
        eventLimit: true, // allow "more" link when too many events
        events: eventos,
        dayClick: function (date, jsEvent, view, resourceObj) {

            $('#calendario').fullCalendar('changeView', 'agendaDay', date);

        },
        eventClick: function (calEvent, jsEvent, view) {

            alert('Event: ' + calEvent.title);
            alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
            alert('View: ' + view.name);

            // change the border color just for fun
//            $(this).css('border-color', 'red');

        }
    });

}
