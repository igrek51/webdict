import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from '../errors/page-not-found.component';
import {TopWordComponent} from "../top/top-word.component";
import {AddWordComponent} from "../addword/add-word.component";
import {SettingsComponent} from "../settings/settings.component";
import {StatisticsComponent} from "../stats/statistics.component";
import {WordsListComponent} from "../wordslist/words-list.component";
import {AuthGuardService} from "./auth-guard.service";

const routes: Routes = [
  {path: '', redirectTo: '/top', pathMatch: 'full'},
  {path: 'top', component: TopWordComponent, canActivate: [AuthGuardService]},
  {path: 'list', component: WordsListComponent, canActivate: [AuthGuardService]},
  {path: 'add', component: AddWordComponent, canActivate: [AuthGuardService]},
  {path: 'stats', component: StatisticsComponent, canActivate: [AuthGuardService]},
  {path: 'settings', component: SettingsComponent},
  {path: '**', component: PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
