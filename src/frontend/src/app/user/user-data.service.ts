import {EventEmitter, Injectable, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Injectable()
export class UserDataService implements OnInit {

  // TODO use JWT token
  userId: number;
  username: string;
  dictionary: string;
  reversedDictionary: boolean;
  changes = new EventEmitter<any>();
  storage = sessionStorage; // sessionStorage or localStorage

  constructor(private router: Router) {
    if (this.storage.getItem('userId')) {
      this.userId = parseInt(this.storage.getItem('userId'));
      this.username = this.storage.getItem('username');
    }
    this.setDictionary(this.storage.getItem('dictionaryCode'));
  }

  ngOnInit() {
  }

  get dictionaryCode(): string {
    return this.dictionary + (this.reversedDictionary ? '-r' : '');
  }

  get dictionaryDisplayName(): string {
    if (this.dictionary) {
      if (this.reversedDictionary) {
        return this.dictionary.replace('-', '<-');
      } else {
        return this.dictionary.replace('-', '->');
      }
    } else {
      return null;
    }
  }

  setUser(userId: number, username: string) {
    this.userId = userId;
    this.username = username;

    this.storage.setItem('userId', '' + this.userId);
    this.storage.setItem('username', this.username);

    this.changes.emit(); // emit settings changed
  }

  setDictionary(dictionaryCode: string) {
    if (dictionaryCode) {
      this.dictionary = dictionaryCode.substr(0, 5);
      this.reversedDictionary = dictionaryCode.endsWith('-r');
      this.storage.setItem('dictionaryCode', dictionaryCode);
    }
    this.changes.emit(); // emit settings changed
  }

  loggedIn(): boolean {
    return this.userId != null && this.dictionary != null;
  }

  logOut() {
    this.userId = null;
    this.username = null;
    this.dictionary = null;
    this.reversedDictionary = null;

    this.storage.clear();
    this.changes.emit(); // emit settings changed

    this.router.navigate([this.router.url]);
  }
}
