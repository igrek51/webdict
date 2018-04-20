import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {WordRank} from "./WordRank";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/observable/of';

@Injectable()
export class WordRankService {

  constructor(private http: HttpClient) {
  }

  getAllWordRanks(): Observable<WordRank[]> {

    let userId = 1;
    let dictionaryCode = 'en-pl';
    const url = `/api/rank/all/${userId}/${dictionaryCode}`;

    return this.http.get<WordRank[]>(url);
  }

}
