$(document).ready(function () {
    $("#inputFilter").on("keyup", function () {
        var inputValue = $(this).val().toLowerCase();
        $("#productTable tr#products").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(inputValue) > -1)
        });
    });


    $('#it').click(function () {
        $('ul.dropdown-menu').toggle();
    });

    var pathName = window.location.pathname;
    if (pathName == "/registration") {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $('#register-form-link').addClass('active');
    }

});

$(function () {
    $('#login-form-link').click(function (e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

    $('#register-form-link').click(function (e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
});

