import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WeatherFormComponent } from './components/weather-form/weather-form.component';
import { WeatherRequestsComponent } from './components/weather-requests/weather-requests.component';
import { WeatherDetailsComponent } from './components/weather-details/weather-details.component';
import { HttpClientModule } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';

@NgModule({
  declarations: [
    AppComponent,
    WeatherFormComponent,
    WeatherRequestsComponent,
    WeatherDetailsComponent    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxPaginationModule    
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
