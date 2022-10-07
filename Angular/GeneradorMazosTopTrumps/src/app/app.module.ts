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
import { CardComponent } from './card/card.component';
import {AutocompleteLibModule} from 'angular-ng-autocomplete';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { LoadComponent } from './load/load.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { KeywordComponent } from './keyword/keyword.component';



@NgModule({
  declarations: [													
    AppComponent,
      LoginComponent,
      HomeComponent,
      BuscarComponent,
      DeckComponent,
      CardComponent,
      LoadComponent,
      KeywordComponent
   ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    AutocompleteLibModule,
    BrowserAnimationsModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    MatInputModule,
    MatDialogModule,
    MatButtonModule,
    MatTableModule,

  ],

  entryComponents: [
    LoadComponent
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
