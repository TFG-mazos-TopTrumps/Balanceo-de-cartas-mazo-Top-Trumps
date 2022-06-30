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
  formulario: FormGroup;
  cards: number;
  nCategories: number;
  constructor(private fb: FormBuilder, private route : ActivatedRoute, private router:Router) { 

    this.cards = route.snapshot.params["cards"];
    this.nCategories = route.snapshot.params["categories"];
  }

  ngOnInit() {

    this.formCategories();

    while(this.i<=this.nCategories) {
      this.addCategorie();
      this.i++;
    }
  }

  formCategories() {
    this.formulario = this.fb.group({
      categories: this.fb.array([])
    });
  }

  get categories(): FormArray {
    return this.formulario.get('categories') as FormArray;
  }

  addCategorie() {
    const c = this.fb.group({
      categorie: new FormControl('')
    });
    this.categories.push(c);
  }

  nextCards() {
    this.router.navigate(['/card', this.cards]);
  }
}
