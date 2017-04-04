(function($) {
    $(function() {
        $('.button-collapse').sideNav();
    }); // end of document ready
})(jQuery); // end of jQuery name space

//Iniciar dropdow
$(".dropdown-button").dropdown();

//inicia tabs da janela
$(document).ready(function() {
    $('ul.tabs').tabs();
});

//inicia janelas modais
$(document).ready(function() {
    $('.modal').modal();
});
$(document).ready(function() {
    $('#modal-horarios').modal();
});