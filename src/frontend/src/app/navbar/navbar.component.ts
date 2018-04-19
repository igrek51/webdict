import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  activeTab;
  settings_user = null;
  settings_dictionary = null;

  constructor() {
  }

  ngOnInit() {
  }

}
