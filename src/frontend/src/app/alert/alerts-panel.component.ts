import {Component, OnInit} from '@angular/core';
import {Alert} from "./Alert";
import {AlertService} from "./alert.service";

@Component({
  selector: 'app-alerts-panel',
  templateUrl: './alerts-panel.component.html',
  styleUrls: ['./alerts-panel.component.css']
})
export class AlertsPanelComponent implements OnInit {

  alerts: Alert[] = [];

  constructor(private alertService: AlertService) {
  }

  ngOnInit() {
    this.alertService.getAlerts().subscribe(alert => {
        this.alerts.push(alert);
        console.log(alert);
      }
    );
  }

}
