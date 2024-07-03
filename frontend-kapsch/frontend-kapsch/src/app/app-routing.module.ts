import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { WeatherFormComponent } from './components/weather-form/weather-form.component';
import { WeatherRequestsComponent } from './components/weather-requests/weather-requests.component';
import { WeatherDetailsComponent } from './components/weather-details/weather-details.component';

const routes: Routes = [
  { path: '', redirectTo: '/weather-form', pathMatch: 'full' },
  { path: 'weather-form', component: WeatherFormComponent },
  { path: 'weather-requests', component: WeatherRequestsComponent },
  { path: 'weather-details/:id', component: WeatherDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }