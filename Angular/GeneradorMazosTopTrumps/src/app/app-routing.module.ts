import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BuscarComponent } from './buscar/buscar.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component'
import { DeckComponent } from './deck/deck.component';
import { CardComponent } from './card/card.component';
import { KeywordComponent } from './keyword/keyword.component';


const routes: Routes = [
  
  {
    path : '', 
    component : LoginComponent
  },
  
  {
    path : 'home', 
    component : HomeComponent
  },

  {
    path : 'deck', 
    component : DeckComponent
  },

  {
    path : 'keyword/:cards/:categories/:valueMin/:valueMax', 
    component : KeywordComponent
  },

  {
    path : 'buscar', 
    component : BuscarComponent
  },
  
  {
    path : 'card/:cards/:categories/:valueMin/:valueMax', 
    component : CardComponent
  }
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
// declarations: [	
,  //  "AutocompleteContentDirective"
  //
     
   
})

export class AppRoutingModule { }
