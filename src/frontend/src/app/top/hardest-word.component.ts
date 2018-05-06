import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {WordRank} from "../wordrank/WordRank";
import {UserDataService} from "../user/user-data.service";

declare var $: any;

const fadeTime = 500;

@Component({
  selector: 'app-hardest-word',
  templateUrl: './hardest-word.component.html',
  styleUrls: ['./hardest-word.component.css']
})
export class HardestWordComponent implements OnInit {

  topWord: WordRank;
  reversedDictionary = false;
  displayWordName;
  displayDefinition;

  constructor(private http: HttpClient, private userData: UserDataService) {
  }

  ngOnInit() {
    this.nextTopWordInit();
  }

  nextTopWordInit() {
    let userId = this.userData.userId;
    let dictionaryCode = this.userData.dictionaryCode;
    const url = `/api/rank/hardest/${userId}/${dictionaryCode}`;
    return this.http.get<WordRank>(url).subscribe(
      response => this.onTopWordReceived(response),
      err => console.log(err)
    );
  }

  onTopWordReceived(topWord) {
    this.topWord = topWord;
    if (this.topWord == null) {
      $("#button-skip").hide();
      $("#button-check").hide();
      $("#button-answer-correct").hide();
      $("#button-answer-wrong").hide();
    } else {
      if (this.reversedDictionary) {
        this.displayWordName = '';
        this.displayDefinition = this.topWord.definition;
      } else {
        this.displayWordName = this.topWord.wordName;
        this.displayDefinition = '';
      }
      $("#button-check").show();
      $("#button-answer-correct").hide();
      $("#button-answer-wrong").hide();
    }
  }

  checkAnswer() {
    $("#button-check").hide();
    $("#button-answer-correct").fadeIn(fadeTime);
    $("#button-answer-wrong").fadeIn(fadeTime);

    this.displayWordName = this.topWord.wordName;
    this.displayDefinition = this.topWord.definition;

    if (!this.reversedDictionary) {
      $("#dict-definition")
        .hide()
        .fadeIn(fadeTime);
    } else {
      $("#dict-word")
        .hide()
        .fadeIn(fadeTime);
    }
  }

  answerCorrect() {
    this.clickedWordAction('answer/correct');
  }

  answerWrong() {
    this.clickedWordAction('answer/wrong');
  }

  skipWord() {
    this.clickedWordAction('skip');
  }

  clickedWordAction(endpointAction) {
    let rankId = this.topWord.rankId;
    const url = `/api/rank/${rankId}/${endpointAction}`;
    this.http.post<WordRank>(url, null).subscribe(
      () => {
        this.nextTopWordInit();
      },
      err => console.log(err)
    );
  }
}
