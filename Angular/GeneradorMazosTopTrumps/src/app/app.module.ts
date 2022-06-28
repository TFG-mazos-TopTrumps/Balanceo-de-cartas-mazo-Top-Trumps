import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BuscarComponent } from './buscar/buscar.component';
import { DeckComponent } from './deck/deck.component';
import { CategoriesComponent } from './categories/categories.component';
import { CardComponent } from './card/card.component';

@NgModule({
  declarations: [									
    AppComponent,
      LoginComponent,
      HomeComponent,
      BuscarComponent,
      DeckComponent,
      CategoriesComponent,
      CardComponent
   ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
