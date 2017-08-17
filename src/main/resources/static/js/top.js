$(document).ready(function () {
    // bind action to buttons
    $("#button-check").click(function () {
        checkAnswer();
    });

    $("#button-skip").click(function () {
        clickedEntryAction('skip');
    });
    $("#button-answer-correct").click(function () {
        clickedEntryAction('answer/correct');
    });
    $("#button-answer-wrong").click(function () {
        clickedEntryAction('answer/wrong');
    });
});

function checkAnswer() {
    var fadeTime = 500;
    $("#button-answer-correct").fadeIn(fadeTime);
    $("#button-answer-wrong").fadeIn(fadeTime);
    $("#button-check").hide();
    $("#dict-definition")
        .hide()
        .text(wordDefinition)
        .fadeIn(fadeTime);
}

function ajaxPutRequest(endpoint, onSuccess) {
    $.ajax({
        type: "POST",
        url: endpoint,
        success: function (data) {
            onSuccess();
        },
        error: function () {
            alert('AJAX Error occured');
        }
    });
}

function clickedEntryAction(endpointAction) {
    ajaxPutRequest(window.location.href + '/../rest/rank/' + rankId + '/' + endpointAction, function () {
        refreshPage();
    });
}

function refreshPage() {
    location.reload();
}