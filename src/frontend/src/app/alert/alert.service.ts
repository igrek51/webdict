import {Injectable} from '@angular/core';
import {Alert} from "./Alert";
import {Observable} from "rxjs/Observable";
import {Subject} from "rxjs/Subject";

@Injectable()
export class AlertService {

  private subject = new Subject<Alert>();

  success(message: string) {
    this.subject.next(new Alert(message, 'alert-success'));
  }

  info(message: string) {
    this.subject.next(new Alert(message, 'alert-info'));
  }

  warn(message: string) {
    this.subject.next(new Alert(message, 'alert-warning'));
  }

  error(message: string) {
    this.subject.next(new Alert(message, 'alert-danger'));
  }

  getAlerts(): Observable<Alert> {
    return this.subject.asObservable();
  }

}
