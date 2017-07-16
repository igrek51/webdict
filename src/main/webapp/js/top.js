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
        .text(dictEntryDefinition)
        .fadeIn(fadeTime);
}

function ajaxPutRequest(endpoint, onSuccess) {
    $.ajax({
        type: "PUT",
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
    ajaxPutRequest('../rest/entry/' + dictEntryId + '/' + endpointAction, function () {
        refreshPage();
    });
}

function refreshPage() {
    // window.location.replace("top");
    location.reload();
}