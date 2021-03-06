import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {WordRank} from "../wordrank/WordRank";
import {UserDataService} from "../user/user-data.service";
import {ActivatedRoute}from "@angular/router";
import {Router}from "@angular/router";

declare var $: any;

const fadeTime = 500;

@Component({
  selector: 'app-top-word',
  templateUrl: './top-word.component.html',
  styleUrls: ['./top-word.component.css']
})
export class TopWordComponent implements OnInit {

  topWord: WordRank;
  displayWordName;
  displayDefinition;
sortby;

constructor(private http: HttpClient, private userData: UserDataService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    // force to reload on url change
    this.router.routeReuseStrategy.shouldReuseRoute = function(){
      return false;
    };

    this.sortby = this.route.snapshot.paramMap.get("sortby")
    this.nextTopWordInit();
  }

  nextTopWordInit() {
    let userId = this.userData.userId;
    let dictionaryCode = this.userData.dictionaryCode;
    let sortby = this.sortby
    const url = `/api/rank/${sortby}/${userId}/${dictionaryCode}`;
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
      if (this.topWord.reversedDictionary) {
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

    if (!this.topWord.reversedDictionary) {
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
