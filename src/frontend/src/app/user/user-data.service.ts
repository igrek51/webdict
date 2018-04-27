import {EventEmitter, Injectable, OnInit} from '@angular/core';

@Injectable()
export class UserDataService implements OnInit {

  // TODO use JWT token
  userId: number;
  username: string;
  dictionary: string;
  reversedDictionary: boolean;

  changes = new EventEmitter<any>();

  constructor() {
    if (sessionStorage.getItem('userId')) {
      this.userId = parseInt(sessionStorage.getItem('userId'));
      this.username = sessionStorage.getItem('username');
    }
    this.setDictionary(sessionStorage.getItem('dictionaryCode'));
  }

  ngOnInit() {
  }

  get dictionaryCode(): string {
    return this.dictionary + (this.reversedDictionary ? '-r' : '');
  }

  get dictionaryDisplayName(): string {
    if (this.reversedDictionary) {
      return this.dictionary.replace('-', '<-');
    } else {
      return this.dictionary.replace('-', '->');
    }
  }

  setUser(userId: number, username: string) {
    this.userId = userId;
    this.username = username;

    sessionStorage.setItem('userId', '' + this.userId);
    sessionStorage.setItem('username', this.username);
  }

  setDictionary(dictionaryCode: string) {
    if (dictionaryCode) {
      this.dictionary = dictionaryCode.substr(0, 5);
      this.reversedDictionary = dictionaryCode.endsWith('-r');
      sessionStorage.setItem('dictionaryCode', dictionaryCode);
    }
  }

  loggedIn(): boolean {
    return this.userId != null;
  }
}
