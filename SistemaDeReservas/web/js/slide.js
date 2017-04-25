$('.carousel.carousel-slider').carousel({fullWidth: true});
$(document).ready(function () {
    $('.slider').slider({indicators: false, height: 400, transition: 800, interval: 5000});
});
$('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15 // Creates a dropdown of 15 years to control year
});
$(document).ready(function () {
    $('select').material_select();
});
            