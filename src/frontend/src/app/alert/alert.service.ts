import {Injectable} from '@angular/core';
import {Alert} from "./Alert";
import {Observable} from "rxjs/Observable";
import {Subject} from "rxjs/Subject";

@Injectable()
export class AlertService {

  private subject = new Subject<Alert>();
  private keepAfterNavigationChange = false;

  // constructor(private router: Router) {
  //   // clear alert message on route change
  //   router.events.subscribe(event => {
  //     if (event instanceof NavigationStart) {
  //       if (this.keepAfterNavigationChange) {
  //         // only keep for a single location change
  //         this.keepAfterNavigationChange = false;
  //       } else {
  //         // clear alert
  //         // this.subject.next();
  //       }
  //     }
  //   });
  // }

  success(message: string, keepAfterNavigationChange = false) {
    this.keepAfterNavigationChange = keepAfterNavigationChange;
    this.subject.next(new Alert(message, 'alert-success'));
  }

  error(message: string, keepAfterNavigationChange = false) {
    this.keepAfterNavigationChange = keepAfterNavigationChange;
    this.subject.next(new Alert(message, 'alert-error'));
  }

  getAlerts(): Observable<Alert> {
    return this.subject.asObservable();
  }
}
