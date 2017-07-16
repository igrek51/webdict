$(document).ready(function () {
    // $("#inner").fadeIn(1000);

    // bind action to buttons
    $("#button-check").click(function () {
        $("#dict-definition").fadeIn(1000);
        $("#button-answer-correct").fadeIn(1000);
        $("#button-answer-wrong").fadeIn(1000);
        $("#button-check").hide();
    });
});