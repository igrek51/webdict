import {Component, OnInit} from '@angular/core';
import {DictionaryStatisticsDTO} from "../../stats/DictionaryStatisticsDTO";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-statistics',
  templateUrl: './statistics.component.html',
  styleUrls: ['./statistics.component.css']
})
export class StatisticsComponent implements OnInit {

  dictStats: DictionaryStatisticsDTO[] = [];
  userId = 1;
  dictionaryCode = 'en-pl';

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    const url = `/api/stats/${this.userId}/${this.dictionaryCode}`;
    return this.http.get<DictionaryStatisticsDTO[]>(url).subscribe(
      response => this.dictStats = response,
      err => console.log(err)
    );
  }

}
