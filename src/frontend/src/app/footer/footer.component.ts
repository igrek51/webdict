import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {EnvironmentInfo} from "./EnvironmentInfo";

const infoUrl = 'http://localhost:8081/api/info';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css']
})
export class FooterComponent implements OnInit {

  environmentInfo: EnvironmentInfo;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.http.get<EnvironmentInfo>(infoUrl).subscribe(data => this.environmentInfo = data);
  }

}
