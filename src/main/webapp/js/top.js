$(document).ready(function () {
    // bind action to buttons
    $("#button-check").click(function () {
        $("#dict-definition").fadeIn(1000);
        $("#button-answer-correct").fadeIn(1000);
        $("#button-answer-wrong").fadeIn(1000);
        $("#button-check").hide();
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
    ajaxPutRequest('/rest/entry/' + dictEntryId + '/' + endpointAction, function () {
        refreshPage();
    });
}

function refreshPage() {
    // window.location.replace("top");
    location.reload();
}