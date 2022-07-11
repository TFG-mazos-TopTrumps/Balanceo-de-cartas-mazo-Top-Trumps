import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {

  i:number=1;
  category: string;
  cards: number;
  nCategories: number;
  categories: Array<string> = [];
  constructor(private route : ActivatedRoute, private router:Router) { 

    this.cards = route.snapshot.params["cards"];
    this.nCategories = route.snapshot.params["categories"];
  }

  ngOnInit() {

    
  }

  generateCategories() {
    if(this.i<this.nCategories) {

      this.categories.push(this.category);
      
      this.category="";
      this.i++;
      console.log(this.categories);
    }
    else {
      this.nextCards();
    }
  }


  

  nextCards() {
    this.router.navigate(['/card', this.cards]);
  }
}
