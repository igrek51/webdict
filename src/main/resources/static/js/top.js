$(document).ready(function () {
    // bind action to buttons
    $("#button-check").click(function () {
        checkAnswer();
    });

    $("#button-skip").click(function () {
        clickedWordAction('skip');
    });
    $("#button-answer-correct").click(function () {
        clickedWordAction('answer/correct');
    });
    $("#button-answer-wrong").click(function () {
        clickedWordAction('answer/wrong');
    });

    startUp();
});

function startUp() {
    if (!reversedDictionary) {
        $("#dict-word")
            .text(wordName)
    } else {
        $("#dict-definition")
            .text(wordDefinition)
    }
}

function checkAnswer() {
    var fadeTime = 500;
    $("#button-answer-correct").fadeIn(fadeTime);
    $("#button-answer-wrong").fadeIn(fadeTime);
    $("#button-check").hide();

    if (!reversedDictionary) {
        $("#dict-definition")
            .hide()
            .text(wordDefinition)
            .fadeIn(fadeTime);
    } else {
        $("#dict-word")
            .hide()
            .text(wordName)
            .fadeIn(fadeTime);
    }
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

function clickedWordAction(endpointAction) {
    ajaxPutRequest(window.location.href + '/../api/rank/' + rankId + '/' + endpointAction, function () {
        refreshPage();
    });
}

function refreshPage() {
    location.reload();
}