import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-top-word',
  templateUrl: './top-word.component.html',
  styleUrls: ['./top-word.component.css']
})
export class TopWordComponent implements OnInit {

  wordrank = null;
  rankId;
  wordName;
  wordDefinition;
  reversedDictionary;

  constructor() {
  }

  ngOnInit() {
    // this.rankId = this.wordrank?.rankId;
    // this.wordName = this.wordrank.wordName;
    // this.wordDefinition = this.wordrank.definition;
    // this.reversedDictionary = this.wordrank.reversedDictionary;

    // $("#button-check").click(function () {
    //   checkAnswer();
    // });
    //
    // $("#button-skip").click(function () {
    //   clickedWordAction('skip');
    // });
    // $("#button-answer-correct").click(function () {
    //   clickedWordAction('answer/correct');
    // });
    // $("#button-answer-wrong").click(function () {
    //   clickedWordAction('answer/wrong');
    // });
    //
    // startUp();
  }

  // startUp() {
  //   if (!reversedDictionary) {
  //     $("#dict-word")
  //       .text(wordName)
  //   } else {
  //     $("#dict-definition")
  //       .text(wordDefinition)
  //   }
  // }
  //
  // checkAnswer() {
  //   var fadeTime = 500;
  //   $("#button-answer-correct").fadeIn(fadeTime);
  //   $("#button-answer-wrong").fadeIn(fadeTime);
  //   $("#button-check").hide();
  //
  //   if (!reversedDictionary) {
  //     $("#dict-definition")
  //       .hide()
  //       .text(wordDefinition)
  //       .fadeIn(fadeTime);
  //   } else {
  //     $("#dict-word")
  //       .hide()
  //       .text(wordName)
  //       .fadeIn(fadeTime);
  //   }
  // }
  //
  // ajaxPutRequest(endpoint, onSuccess) {
  //   $.ajax({
  //     type: "POST",
  //     url: endpoint,
  //     success: function (data) {
  //       onSuccess();
  //     },
  //     error: function () {
  //       alert('AJAX Error occured');
  //     }
  //   });
  // }
  //
  // clickedWordAction(endpointAction) {
  //   ajaxPutRequest(window.location.href + '/../api/rank/' + rankId + '/' + endpointAction, function () {
  //     refreshPage();
  //   });
  // }
  //
  // refreshPage() {
  //   location.reload();
  // }

}
