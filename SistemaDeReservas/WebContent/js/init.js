$('.carousel.carousel-slider').carousel({fullWidth: true});

//inicia tabs da janela
$(document).ready(function () {
    $('ul.tabs').tabs();
});

//inicia janelas modais
$(document).ready(function () {
    $('.modal').modal();
});

$( document ).ready(function(){$(".button-collapse").sideNav();});


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

$(document).ready(function () {
    $('select').material_select();
});

$('.datepicker').pickadate({
	monthsFull: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
    monthsShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
    weekdaysFull: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sabádo'],
    weekdaysShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sab'],
    today: 'Hoje',
    clear: 'Limpar',
    close: 'Pronto',
    labelMonthNext: 'Próximo mês',
    labelMonthPrev: 'Mês anterior',
    labelMonthSelect: 'Selecione um mês',
    labelYearSelect: 'Selecione um ano',
    format: 'dd/mm/yyyy',
    selectMonths: true, 
    selectYears: 1,
    min: new Date().getTime()
  });
