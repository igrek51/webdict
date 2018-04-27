import {Component, OnInit} from '@angular/core';
import {UserDataService} from "../user/user-data.service";
import {HttpClient} from "@angular/common/http";
import "rxjs/add/operator/map";
import {AlertService} from "../alert/alert.service";


@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  userId: number;
  dictionaryCode: string;
  users = [];
  dicts = [];

  constructor(private http: HttpClient, private userData: UserDataService, private alertService: AlertService) {
    if (this.userData.loggedIn()) {
      this.userId = this.userData.userId;
      this.dictionaryCode = this.userData.dictionaryCode;
    }
  }

  ngOnInit() {
    let url = `/api/user`;
    this.http.get<any[]>(url).subscribe(
      response => {
        this.users = response;
        if (!this.userId && this.users.length > 0) { // set first entry by default
          this.userId = this.users[0].id;
        }
      },
      err => console.log(err)
    );
    url = `/api/dictionary`;
    this.http.get<any[]>(url).subscribe(
      response => this.buildDicts(response),
      err => console.log(err)
    );
  }

  buildDicts(dictsRaw: any[]) {
    this.dicts = [];
    for (let dictRaw of dictsRaw) {
      this.dicts.push({
        code: dictRaw.sourceLanguage + '-' + dictRaw.targetLanguage,
        displayName: dictRaw.sourceLanguage + ' -> ' + dictRaw.targetLanguage
      });
      this.dicts.push({
        code: dictRaw.sourceLanguage + '-' + dictRaw.targetLanguage + '-r',
        displayName: dictRaw.sourceLanguage + ' <- ' + dictRaw.targetLanguage
      });
    }
    if (!this.dictionaryCode && this.dicts.length > 0) { // set first entry by default
      this.dictionaryCode = this.dicts[0].code;
    }
  }

  saveSettings() {
    let username = this.users
      .filter(user => user.id == this.userId)
      .map((user): string => user.login)
      .pop();
    if (username == null) { // null or undefined
      this.alertService.error('No user has been selected.');
      return;
    }
    if (this.dictionaryCode == undefined || this.dictionaryCode == 'undefined') {
      this.alertService.error('No dictionary has been selected.');
      return;
    }
    this.userData.setUser(this.userId, username);
    this.userData.setDictionary(this.dictionaryCode);
    this.userData.changes.emit(); // emit settings changed
    this.alertService.success('Settings have been successfully saved.');
  }

}
