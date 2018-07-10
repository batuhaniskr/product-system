$(document).ready(function() {
    $("#inputFilter").on("keyup", function () {
        var inputValue = $(this).val().toLowerCase();
        $("#productTable tr#products").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(inputValue) > -1)
        });
    });

    $('select.form-control').combobox();

    $('#it').click(function(e){
        $('ul.dropdown-menu').toggle();
    });
});