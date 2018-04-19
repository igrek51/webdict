import {Component, OnInit} from '@angular/core';
import {Alert} from "./Alert";

@Component({
  selector: 'app-alerts-panel',
  templateUrl: './alerts-panel.component.html',
  styleUrls: ['./alerts-panel.component.css']
})
export class AlertsPanelComponent implements OnInit {

  alerts: Alert[];

  constructor() {
  }

  ngOnInit() {
  }

}
