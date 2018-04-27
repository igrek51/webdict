import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {UserDataService} from "../user/user-data.service";
import {AlertService} from "../alert/alert.service";

@Injectable()
export class AuthGuardService implements CanActivate {

  constructor(private router: Router, private userDataService: UserDataService, private alertService: AlertService) { }

  canActivate() {
    if (!this.userDataService.loggedIn()) {
      this.router.navigate(['/settings']).then(() => {
        this.alertService.warn('Choose an user and dictionary first.');
      });
    }
    return true;
  }

}
