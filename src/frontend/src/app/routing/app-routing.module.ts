import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PageNotFoundComponent} from '../errors/page-not-found.component';
import {TopWordComponent} from "../pages/top-word.component";
import {AddWordComponent} from "../pages/add-word.component";
import {SettingsComponent} from "../pages/settings.component";
import {StatisticsComponent} from "../pages/statistics.component";
import {WordsListComponent} from "../pages/words-list.component";

const routes: Routes = [
  {path: '', redirectTo: '/top', pathMatch: 'full'},
  {path: 'top', component: TopWordComponent},
  {path: 'list', component: WordsListComponent},
  {path: 'add', component: AddWordComponent},
  {path: 'settings', component: SettingsComponent},
  {path: 'stats', component: StatisticsComponent},
  {path: '**', component: PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
