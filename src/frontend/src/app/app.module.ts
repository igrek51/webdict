import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {AppComponent} from './app.component';
import {PageNotFoundComponent} from './errors/page-not-found.component';
import {AppRoutingModule} from "./routing/app-routing.module";
import {TopWordComponent} from "./pages/top-word.component";
import {AddWordComponent} from "./pages/addword/add-word.component";
import {SettingsComponent} from "./pages/settings.component";
import {StatisticsComponent} from "./pages/statistics.component";
import {WordsListComponent} from "./pages/words-list.component";
import {FooterComponent} from "./footer/footer.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {AlertsPanelComponent} from "./alert/alerts-panel.component";
import {HttpClientModule} from "@angular/common/http";
import {AlertService} from "./alert/alert.service";
import {WordRankService} from "./wordrank/word-rank.service";

@NgModule({
  declarations: [
    AppComponent,
    PageNotFoundComponent,
    TopWordComponent,
    AddWordComponent,
    SettingsComponent,
    StatisticsComponent,
    WordsListComponent,
    FooterComponent,
    NavbarComponent,
    AlertsPanelComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    AlertService,
    WordRankService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
